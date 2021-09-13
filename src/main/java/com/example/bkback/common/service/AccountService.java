package com.example.bkback.common.service;

import com.example.bkback.db.dto.ProfileDto;
import com.example.bkback.db.entity.Account;
import com.example.bkback.db.entity.Post;
import com.example.bkback.db.entity.Postlist;
import com.example.bkback.db.repository.Intimacy.IntimacyRepository;
import com.example.bkback.db.repository.account.AccountRepository;
import com.example.bkback.db.repository.follow.FollowRepository;
import com.example.bkback.db.repository.post.PostRepository;
import com.example.bkback.db.searchCondition.PostSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final IntimacyRepository intimacyRepository;

    public ProfileDto getProfileByUsername(String username) {
        ProfileDto profile = accountRepository.getProfile(username);

        PostSearchCondition condition = new PostSearchCondition();
        condition.setWriterAccount(username);
        profile.setWritePostAsAccount(postRepository.getPostListByWriter(condition));

        condition = new PostSearchCondition();
        condition.setWriterClan(username);
        profile.setWritePostAsClan(postRepository.getPostListByWriter(condition));

        profile.setFollowee(followRepository.getFolloweeByUsername(username));
        profile.setFollower(followRepository.getFollowerByUsername(username));

        profile.setFriend(intimacyRepository.getFriendByUsername(username));

        return profile;
    }
}
