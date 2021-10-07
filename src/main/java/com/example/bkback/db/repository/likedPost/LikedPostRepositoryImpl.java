package com.example.bkback.db.repository.likedPost;

import com.example.bkback.db.entity.QLikedPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.example.bkback.db.entity.QLikedPost.likedPost1;

@Repository
@RequiredArgsConstructor
public class LikedPostRepositoryImpl implements LikedPostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteById(String postId, String userId) {
        queryFactory
                .delete(likedPost1)
                .where(likedPost1.likedPost().id.eq(UUID.fromString(postId))
                        .and(likedPost1.likerAccount().id.eq(UUID.fromString(userId)))
                )
                .execute();
    }
}
