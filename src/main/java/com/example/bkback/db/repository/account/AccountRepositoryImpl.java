package com.example.bkback.db.repository.account;

import com.example.bkback.db.dto.AccountDto;
import com.example.bkback.db.dto.AuthenticationDto;
import com.example.bkback.db.dto.ProfileDto;
import com.example.bkback.db.entity.Account;
import com.example.bkback.db.entity.QPostlist;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.bkback.db.entity.QAccount.account;
import static com.example.bkback.db.entity.QPost.post;
import static com.example.bkback.db.entity.QPostlist.postlist;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public AuthenticationDto findAuthInfoByUsername(String s) {
        Tuple tuple = queryFactory
                .select(
                        account.username,
                        account.password
                )
                .from(account)
                .where(account.username.eq(s))
                .fetchFirst();
        return new AuthenticationDto(tuple.get(account.username), tuple.get(account.password));
    }

    @Override
    public AccountDto findAccountByUsername(String s) {
        Tuple tuple = queryFactory
                .select(
                        account.id,
                        account.username,
                        account.birth,
                        account.profileImage
                )
                .from(account)
                .where(account.username.eq(s))
                .fetchOne();
        return new AccountDto(
                tuple.get(account.id),
                tuple.get(account.username),
                tuple.get(account.birth),
                tuple.get(account.profileImage)
        );
    }

    @Override
    public ProfileDto getProfile(String s) {
        Tuple tuple = queryFactory
                .select(
                        account.id,
                        account.username,
                        account.birth,
                        account.profileImage
                )
                .from(account)
                .distinct()
                .where(account.username.eq(s))
                .fetchOne();

        return new ProfileDto(
                tuple.get(account.id),
                tuple.get(account.username),
                tuple.get(account.birth),
                tuple.get(account.profileImage)
        );

    }

    @Override
    public Account findByUUID(String id) {
        return queryFactory
                .selectFrom(account)
                .where(account.id.eq(UUID.fromString(id)))
                .fetchFirst();
    }
}
