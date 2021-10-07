package com.example.bkback.db.repository.comment;

import com.example.bkback.db.dto.CommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentRepositoryCustom {
    List<CommentDto> getCommentByPostId(UUID postId);
}
