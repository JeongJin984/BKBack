package com.example.bkback.db.repository.postHashtag;

import com.example.bkback.db.dto.SimplePostDto;
import com.example.bkback.db.entity.PostHashtag;
import com.example.bkback.db.entity.QHashtag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.bkback.db.entity.QPost.post;
import static com.example.bkback.db.entity.QPostHashtag.postHashtag;
import static com.example.bkback.db.entity.QHashtag.hashtag;

@Repository
@RequiredArgsConstructor
public class PostHashtagRepositoryImpl implements PostHashtagRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SimplePostDto> getPostByHashtag(String hashtagName) {
        List<PostHashtag> fetch = queryFactory
                .selectFrom(postHashtag)
                .join(postHashtag.hashtag(), hashtag).fetchJoin()
                .join(postHashtag.post(), post).fetchJoin()
                .distinct()
                .where(postHashtag.hashtag().name.eq(hashtagName))
                .fetch();

        List<SimplePostDto> result = new ArrayList<>();
        for(PostHashtag ph : fetch) {
            result.add(new SimplePostDto(ph.getPost()));
        }
        return result;
    }
}
