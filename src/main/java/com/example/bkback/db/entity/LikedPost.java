package com.example.bkback.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class LikedPost {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account likerAccount;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    private Post likedPost;

    public LikedPost(Account likerAccount, Post likedPost) {
        this.likerAccount = likerAccount;
        this.likedPost = likedPost;
    }
}
