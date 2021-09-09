package com.example.bkback.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    private Set<AccountClan> clanId = new HashSet<>();

    @OneToMany(mappedBy = "writerAccount")
    private Set<Post> writePost = new HashSet<>();

    @OneToMany(mappedBy = "likerAccount")
    private Set<LikedPost> likePost = new HashSet<>();

    @OneToMany(mappedBy = "accountA")
    private Set<Intimacy> friender = new HashSet<>();

    @OneToMany(mappedBy = "accountB")
    private Set<Intimacy> friendee = new HashSet<>();

    @OneToMany(mappedBy = "likerAccount")
    private Set<LikedComment> likedComment = new HashSet<>();
}
