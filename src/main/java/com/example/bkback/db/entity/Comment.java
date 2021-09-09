package com.example.bkback.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String writer;
    private String content;
    private Long likerNumber;
    private UUID parentCommentId;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account writerAccount;

    public Comment(String writer, String content, UUID parentCommentId, Long likerNumber, Post post, Account writerAccount) {
        this.writer = writer;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.likerNumber = likerNumber;
        this.post = post;
        this.writerAccount = writerAccount;
    }

    @OneToMany(mappedBy = "comment")
    private Set<LikedComment> likerAccount = new HashSet<>();
}

