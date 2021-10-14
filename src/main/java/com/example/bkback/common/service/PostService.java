package com.example.bkback.common.service;

import com.example.bkback.db.dto.CommentDto;
import com.example.bkback.db.dto.PostDto;
import com.example.bkback.db.entity.Account;
import com.example.bkback.db.entity.Comment;
import com.example.bkback.db.entity.LikedPost;
import com.example.bkback.db.entity.Post;
import com.example.bkback.db.repository.account.AccountRepository;
import com.example.bkback.db.repository.comment.CommentRepository;
import com.example.bkback.db.repository.likedPost.LikedPostRepository;
import com.example.bkback.db.repository.post.PostRepository;
import com.example.bkback.db.searchCondition.PostSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final AccountRepository accountRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikedPostRepository likedPostRepository;

    public List<PostDto> getAllPost() {
        PostSearchCondition condition = new PostSearchCondition();
        List<PostDto> allPost = postRepository.getAllPost(condition);
        for(PostDto p : allPost) {
            p.setComment(commentRepository.getCommentByPostId(p.getId()));
        }
        return allPost;
    }

    @Transactional
    public PostDto getPostWithComment(UUID postId) {
        PostDto postDto = postRepository.getPostByID(postId);
        List<CommentDto> commentDtos = commentRepository.getCommentByPostId(postId);
        postDto.setComment(commentDtos);
        return postDto;
    }

    public List<PostDto> getAllPostByCategory(String category) {
        PostSearchCondition condition = new PostSearchCondition();
        condition.setCategory(category);
        return postRepository.getAllPost(condition);
    }

    @Transactional
    public PostDto uploadPost(String fileName, String writerId) {
        Account writer = accountRepository.findByUUID(writerId);
        Post post = postRepository.saveAndFlush(new Post(null, fileName, null, 0L, writer, null, null));
        return new PostDto(post);
    }

    @Transactional
    public void likePost(String userId, String postId) {
        Account account = accountRepository.findByUUID(userId);
        Post post = postRepository.findByUUID(postId);
        likedPostRepository.saveAndFlush(new LikedPost(account, post));
    }

    @Transactional
    public void unlikePost(String userId, String postId) {
        likedPostRepository.deleteById(postId, userId);
    }

    @Transactional
    public CommentDto addComment(String userId, String postId, String content) {
        Account account = accountRepository.findByUUID(userId);
        Post post = postRepository.findByUUID(postId);
        Comment comment = commentRepository.saveAndFlush(new Comment(account.getProfileImage(), account.getUsername(), content, null, 0L, post, account, false));
        return new CommentDto(comment);
    }
}
