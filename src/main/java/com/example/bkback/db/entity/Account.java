package com.example.bkback.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Getter @Entity
@NoArgsConstructor
public class Account {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String username;
    private String password;
    private Date birth;
    private String profileImage;

    public Account(String username, String password, Date birth, String profileImage) {
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.profileImage = profileImage;
    }

    @OneToMany(mappedBy = "account")
    private List<AccountClan> clanId = new ArrayList<>();

    @OneToMany(mappedBy = "writerAccount")
    private List<Post> writePost = new ArrayList<>();

    @OneToMany(mappedBy = "likerAccount")
    private List<LikedPost> likePost = new ArrayList<>();

    @OneToMany(mappedBy = "accountA")
    private List<Intimacy> friender = new ArrayList<>();

    @OneToMany(mappedBy = "accountB")
    private List<Intimacy> friendee = new ArrayList<>();

    @OneToMany(mappedBy = "likerAccount")
    private List<LikedComment> likedComment = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Postlist> postlist = new ArrayList<>();
}
