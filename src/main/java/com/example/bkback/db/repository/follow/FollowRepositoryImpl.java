package com.example.bkback.db.repository.follow;

import com.example.bkback.db.dto.AccountDto;
import com.example.bkback.db.entity.Account;
import com.example.bkback.db.entity.Follow;
import com.example.bkback.db.entity.QAccount;
import com.example.bkback.db.entity.QFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.bkback.db.entity.QAccount.account;
import static com.example.bkback.db.entity.QFollow.follow;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountDto> getFolloweeByUsername(String username) {
        List<Follow> fetch = queryFactory
                .selectFrom(follow)
                .join(follow.follower(), account).fetchJoin()
                .where(follow.followeeName.eq(username))
                .fetch();

        List<AccountDto> list = new ArrayList<>();
        for(Follow f : fetch) {
            list.add(new AccountDto(f.getFollower()));
        }
        return list;
    }

    @Override
    public List<AccountDto> getFollowerByUsername(String username) {
        List<Follow> fetch = queryFactory
                .selectFrom(follow)
                .join(follow.followee(), account).fetchJoin()
                .where(follow.followerName.eq(username))
                .fetch();

        List<AccountDto> list = new ArrayList<>();
        for(Follow f : fetch) {
            list.add(new AccountDto(f.getFollowee()));
        }
        return list;
    }
}
