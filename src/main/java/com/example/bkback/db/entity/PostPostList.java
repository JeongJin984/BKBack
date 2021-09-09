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
public class PostPostList {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    Post post;
    String postTitle;
    String postImage;

    @ManyToOne(targetEntity = Postlist.class, fetch = FetchType.LAZY)
    Postlist postlist;
    String postlistTitle;

    public PostPostList(Post post, String postTitle, String postImage, Postlist postlist, String postlistTitle) {
        this.post = post;
        this.postTitle = postTitle;
        this.postImage = postImage;
        this.postlist = postlist;
        this.postlistTitle = postlistTitle;
    }
}
