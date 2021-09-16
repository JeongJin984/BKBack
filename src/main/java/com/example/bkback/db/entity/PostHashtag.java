package com.example.bkback.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class PostHashtag {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Hashtag.class, fetch = FetchType.LAZY)
    private Hashtag hashtag;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    private Post post;

    public PostHashtag(Hashtag hashtag, Post post) {
        this.hashtag = hashtag;
        this.post = post;
    }
}
