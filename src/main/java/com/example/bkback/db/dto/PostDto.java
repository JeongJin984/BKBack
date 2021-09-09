package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Account;
import com.example.bkback.db.entity.Comment;
import com.example.bkback.db.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PostDto {
    private UUID id;
    private String image;
    private Long likedNum;
    private Date createdAt;

    private WriterDto writerAccount;
    private ClanDto writerClan;
    private List<CommentDto> comment = new ArrayList<>();
    private String category;

    public PostDto(Post post) {
        this.id = post.getId();
        this.image = post.getImage();
        this.likedNum = post.getLikedNum();
        this.createdAt = Timestamp.valueOf(post.getCreatedAt());
        this.writerAccount =
                post.getWriterAccount() == null ? null : new WriterDto(post.getWriterAccount());
        this.writerClan =
                post.getWriterClan() == null ? null : new ClanDto(post.getWriterClan());
        this.category = post.getCategory().getName();

        for(Comment c : post.getComment()) {
            comment.add(new CommentDto(c));
        }
    }

    @Data
    static class WriterDto {

        private UUID id;
        private String username;

        public WriterDto(Account account) {
            this.id = account.getId();
            this.username = account.getUsername();
        }
    }
}
