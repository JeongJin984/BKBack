package com.example.bkback.db.repository.postPostlist;

import com.example.bkback.db.dto.SimplePostDto;
import com.example.bkback.db.entity.PostPostList;
import com.example.bkback.db.entity.QPost;
import com.example.bkback.db.entity.QPostPostList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.bkback.db.entity.QPost.post;
import static com.example.bkback.db.entity.QPostPostList.postPostList;

@Repository
@RequiredArgsConstructor
public class PostPostlistRepositoryImpl implements PostPostlistRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SimplePostDto> getPostByPostlistTitle(String title) {
        List<PostPostList> fetch = queryFactory
                .selectFrom(postPostList)
                .join(postPostList.post(), post).fetchJoin()
                .distinct()
                .where(postPostList.postlistTitle.eq(title))
                .fetch();

        List<SimplePostDto> post = new ArrayList<>();
        for(PostPostList ppl : fetch) {
            post.add(new SimplePostDto(ppl.getPost()));
        }
        return post;
    }
}
