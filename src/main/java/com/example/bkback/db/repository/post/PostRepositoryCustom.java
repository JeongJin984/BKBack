package com.example.bkback.db.repository.post;

import com.example.bkback.db.dto.PostDto;
import com.example.bkback.db.dto.SimplePostDto;
import com.example.bkback.db.entity.Post;
import com.example.bkback.db.searchCondition.PostSearchCondition;

import java.util.List;
import java.util.UUID;

public interface PostRepositoryCustom {
    List<PostDto> getAllPost(PostSearchCondition condition);

    PostDto getPostByID(UUID id);

    List<SimplePostDto> getPostListByWriter(PostSearchCondition condition);

    Post findByUUID(String postId);

    void likePost(UUID postId);
}
