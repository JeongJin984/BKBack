package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Comment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CommentDto {
    private UUID id;
    private String writer;
    private Long likerNumber;
    private UUID parentComment;

    public CommentDto(UUID id, String writer, Long likerNumber) {
        this.id = id;
        this.writer = writer;
        this.likerNumber = likerNumber;
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter();
        this.likerNumber = comment.getLikerNumber();
        this.parentComment = comment.getParentCommentId();
    }
}
