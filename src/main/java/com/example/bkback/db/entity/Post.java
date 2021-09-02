package com.example.bkback.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Post {
    @Id @GeneratedValue
    private Long id;

    private String image;
    private String content;

    @ManyToOne
    private Account writer;

    @ManyToOne
    private PostCategory category;

    @ManyToOne
    private Clan group;

    @OneToMany(mappedBy = "likedPost")
    private Set<LikePost> likerAccount = new HashSet<>();
}
