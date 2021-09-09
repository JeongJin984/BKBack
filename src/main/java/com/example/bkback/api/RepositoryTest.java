package com.example.bkback.api;

import com.example.bkback.db.entity.Account;
import com.example.bkback.db.entity.PostCategory;
import com.example.bkback.db.repository.Intimacy.IntimacyRepository;
import com.example.bkback.db.repository.account.AccountRepository;
import com.example.bkback.db.repository.accountClan.AccountClanRepository;
import com.example.bkback.db.repository.clan.ClanRepository;
import com.example.bkback.db.repository.comment.CommentRepository;
import com.example.bkback.db.repository.follow.FollowRepository;
import com.example.bkback.db.repository.likedComment.LikedCommentRepository;
import com.example.bkback.db.repository.likedPost.LikedPostRepository;
import com.example.bkback.db.repository.message.MessageRepository;
import com.example.bkback.db.repository.post.PostRepository;
import com.example.bkback.db.repository.postCategory.PostCategoryRepository;
import com.example.bkback.db.repository.postPostlist.PostPostlistRepository;
import com.example.bkback.db.repository.postlist.PostlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class RepositoryTest {

    private final AccountRepository accountRepository;
    private final AccountClanRepository accountClanRepository;
    private final ClanRepository clanRepository;
    private final CommentRepository commentRepository;
    private final FollowRepository followRepository;
    private final IntimacyRepository intimacyRepository;
    private final LikedCommentRepository likedCommentRepository;
    private final LikedPostRepository likedPostRepository;
    private final MessageRepository messageRepository;
    private final PostRepository postRepository;
    private final PostCategoryRepository postCategoryRepository;
    private final PostlistRepository postlistRepository;
    private final PostPostlistRepository postPostlistRepository;

    @GetMapping("/test")
    void test() {
    }
}
