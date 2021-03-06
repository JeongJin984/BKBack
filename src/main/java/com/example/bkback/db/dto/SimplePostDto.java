package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Post;
import com.google.api.client.util.DateTime;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class SimplePostDto {
    private UUID id;
    private String image;
    private Long likedNum;
    private Date createdAt;

    public SimplePostDto(UUID id, String image, Long likedNum, LocalDateTime createdAt) {
        this.id = id;
        this.image = image;
        this.likedNum = likedNum;
        this.createdAt = Timestamp.valueOf(createdAt);
    }

    public SimplePostDto(Post post) {
        this.id = post.getId();
        this.image = post.getImage();
        this.likedNum = post.getLikedNum();
        this.createdAt = Timestamp.valueOf(post.getCreatedAt());
    }
}
