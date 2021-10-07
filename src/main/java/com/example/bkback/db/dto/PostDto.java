package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Account;
import com.example.bkback.db.entity.Comment;
import com.example.bkback.db.entity.LikedPost;
import com.example.bkback.db.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.*;

@Data
public class PostDto {
    private UUID id;
    private String image;
    private Long likedNum;
    private Date createdAt;

    private WriterDto writerAccount;
    private ClanDto writerClan;
    private List<CommentDto> comment = new ArrayList<>();
    private Set<LikedAccountDto> likerAccount = new HashSet<>();
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
        if(post.getCategory() != null) this.category = post.getCategory().getName();
        for(LikedPost v : post.getLikerAccount()) {
            likerAccount.add(new LikedAccountDto(v.getLikerAccount().getId()));
        }
    }

    @Data
    static class WriterDto {
        private UUID id;
        private String username;
        private String profileImage;

        public WriterDto(Account account) {
            this.id = account.getId();
            this.username = account.getUsername();
            this.profileImage = account.getProfileImage();
        }
    }

    @Data
    static class LikedAccountDto {
        private UUID id;

        public LikedAccountDto(UUID id) {
            this.id = id;
        }
    }
}
