package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CommentDto {
    private UUID id;
    private String writerProfileImage;
    private String writerName;
    private Long likerNumber;
    private UUID parentComment;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.writerProfileImage = comment.getWriterProfileImage();
        this.writerName = comment.getWriterName();
        this.likerNumber = comment.getLikerNumber();
        this.parentComment = comment.getParentCommentId();
    }
}
