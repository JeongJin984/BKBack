package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CommentDto {
    private UUID id;
    private String writerProfileImage;
    private String writerName;
    private String content;
    private Long likerNumber;
    private LocalDate createdAt;
    private List<CommentDto> replyComment = new ArrayList<>();

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.writerProfileImage = comment.getWriterProfileImage();
        this.writerName = comment.getWriterName();
        this.content = comment.getContent();
        this.likerNumber = comment.getLikerNumber();
        this.createdAt = comment.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for(Comment c :comment.getReplyComment()) {
            replyComment.add(new CommentDto(c));
        };
    }
}
