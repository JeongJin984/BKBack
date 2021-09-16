package com.example.bkback.db.repository.postlist;

import com.example.bkback.db.dto.SimplePostlistDto;
import com.example.bkback.db.entity.Postlist;
import com.example.bkback.db.entity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.bkback.db.entity.QAccount.account;
import static com.example.bkback.db.entity.QPostlist.postlist;

@Repository
@RequiredArgsConstructor
public class PostlistRepositoryImpl implements PostlistRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SimplePostlistDto> getPostlistByUsername(String username) {
        List<Postlist> fetch = queryFactory
                .selectFrom(postlist)
                .join(postlist.owner(), account).fetchJoin()
                .distinct()
                .where(postlist.owner().username.eq(username))
                .fetch();

        List<SimplePostlistDto> postlist = new ArrayList<>();
        for(Postlist pl : fetch) {
            postlist.add(new SimplePostlistDto(pl));
        }
        return postlist;
    }
}
