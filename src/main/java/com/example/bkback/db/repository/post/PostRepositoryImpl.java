package com.example.bkback.db.repository.post;

import com.example.bkback.db.dto.PostDto;
import com.example.bkback.db.dto.SimplePostDto;
import com.example.bkback.db.entity.*;

import com.example.bkback.db.searchCondition.PostSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.bkback.db.entity.QAccount.account;
import static com.example.bkback.db.entity.QClan.clan;
import static com.example.bkback.db.entity.QComment.comment;
import static com.example.bkback.db.entity.QLikedPost.likedPost1;
import static com.example.bkback.db.entity.QPost.post;
import static com.example.bkback.db.entity.QPostCategory.postCategory;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostDto> getAllPost(PostSearchCondition condition) {
        List<Post> posts = queryFactory
                .selectFrom(post)
                .leftJoin(post.category(), postCategory).fetchJoin()
                .leftJoin(post.likerAccount, likedPost1).fetchJoin()
                .leftJoin(post.comment, comment).fetchJoin()
                .leftJoin(post.writerClan(), clan).fetchJoin()
                .leftJoin(post.writerAccount(), account).fetchJoin()
                .distinct()
                .where(
                        postCategoryNameEq(condition.getCategory())
                )
                .fetch();

        List<PostDto> postDtos = new ArrayList<>();
        for(Post p : posts) {
            postDtos.add(new PostDto(p));
        }

        return postDtos;
    }

    @Override
    public List<SimplePostDto> getPostListByWriter(PostSearchCondition condition) {
        List<Post> fetch = queryFactory
                .selectFrom(post)
                .leftJoin(post.writerAccount(), account).fetchJoin()
                .leftJoin(post.writerClan(), clan).fetchJoin()
                .distinct()
                .where(
                        postWriterAccountNameEq(condition.getWriterAccount()),
                        postWriterClanNameEq(condition.getWriterClan())
                )
                .fetch();

        List<SimplePostDto> result = new ArrayList<>();
        for(Post p : fetch) {
            result.add(
                    new SimplePostDto(
                            p.getId(),
                            p.getImage(),
                            p.getLikedNum(),
                            p.getCreatedAt()
            ));
        }
        return result;
    }

    BooleanExpression postCategoryNameEq(String categoryName) {
        return hasText(categoryName) ? postCategory.name.eq(categoryName) : null;
    }

    BooleanExpression postWriterAccountNameEq(String writer) {
        return hasText(writer) ? post.writerAccount().username.eq(writer) : null;
    }

    BooleanExpression postWriterClanNameEq(String writer) {
        return hasText(writer) ? post.writerClan().master.eq(writer) : null;
    }
}

