package com.example.bkback.db.repository.Intimacy;

import com.example.bkback.db.dto.AccountDto;
import com.example.bkback.db.entity.Intimacy;
import com.example.bkback.db.entity.QAccount;
import com.example.bkback.db.entity.QIntimacy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.bkback.db.entity.QAccount.account;
import static com.example.bkback.db.entity.QIntimacy.intimacy;

@Repository
@RequiredArgsConstructor
public class IntimacyRepositoryImpl implements IntimacyRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountDto> getFriendByUsername(String username) {
        List<Intimacy> fetch = queryFactory
                .selectFrom(intimacy)
                .leftJoin(intimacy.accountA(), account).fetchJoin()
                .leftJoin(intimacy.accountB(), account).fetchJoin()
                .distinct()
                .where(intimacy.nameA.eq(username).or(intimacy.nameB.eq(username)))
                .fetch();

        List<AccountDto> friends = new ArrayList<>();
        for(Intimacy v : fetch) {
            if(v.getNameA().equals(username)) {
                friends.add(new AccountDto(v.getAccountB()));
            } else {
                friends.add(new AccountDto(v.getAccountA()));
            }
        }
        return friends;
    }
}
