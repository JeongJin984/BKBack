package com.example.bkback.db.repository.postCategory;

import com.example.bkback.db.dto.AllCategoryPostDto;
import com.example.bkback.db.entity.PostCategory;
import com.example.bkback.db.entity.QPost;
import com.example.bkback.db.entity.QPostCategory;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.bkback.db.entity.QPost.post;
import static com.example.bkback.db.entity.QPostCategory.postCategory;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class PostCategoryRepositoryImpl implements PostCategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}
