package com.example.bkback.db.repository.comment;

import com.example.bkback.db.entity.Comment;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CommonRepository<Comment, UUID>, CommentRepositoryCustom {
}
