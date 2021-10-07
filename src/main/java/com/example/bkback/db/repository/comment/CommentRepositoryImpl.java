package com.example.bkback.db.repository.comment;

import com.example.bkback.db.dto.CommentDto;
import com.example.bkback.db.entity.Comment;
import com.example.bkback.db.entity.QComment;
import com.example.bkback.db.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.bkback.db.entity.QComment.comment;
import static com.example.bkback.db.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentDto> getCommentByPostId(UUID postId) {
        List<Comment> fetch = queryFactory
                .selectFrom(comment)
                .join(comment.post(), post).fetchJoin()
                .leftJoin(comment.replyComment, new QComment("reply")).fetchJoin()
                .distinct()
                .where(comment.post().id.eq(postId).and(comment.isReply.eq(false)))
                .fetch();

        List<CommentDto> result = new ArrayList<>();
        for(Comment c : fetch) {
            result.add(new CommentDto(c));
        }
        return result;
    }
}
