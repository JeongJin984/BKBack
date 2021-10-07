package com.example.bkback.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String writerProfileImage;
    private String writerName;
    private String content;
    private Long likerNumber;
    private Boolean isReply;

    @CreatedDate
    private Date createdAt;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account writerAccount;

    @ManyToOne(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private Comment parentComment;

    public Comment(String writerProfileImage , String writerName, String content, Comment parentComment, Long likerNumber, Post post, Account writerAccount, boolean isReply) {
        this.writerProfileImage = writerProfileImage;
        this.writerName = writerName;
        this.content = content;
        this.parentComment = parentComment;
        this.likerNumber = likerNumber;
        this.post = post;
        this.writerAccount = writerAccount;
        this.isReply = isReply;
    }

    @OneToMany(mappedBy = "comment")
    private Set<LikedComment> likerAccount = new HashSet<>();

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComment = new ArrayList<>();
}

