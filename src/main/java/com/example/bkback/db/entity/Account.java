package com.example.bkback.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Account {
    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private Date birth;
    private String profileImage;

    @OneToMany(mappedBy = "account")
    Set<AccountClan> groupId = new HashSet<>();

    @OneToMany(mappedBy = "writer")
    Set<Post> writePost = new HashSet<>();

    @OneToMany(mappedBy = "likerAccount")
    Set<LikePost> likePost = new HashSet<>();

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
