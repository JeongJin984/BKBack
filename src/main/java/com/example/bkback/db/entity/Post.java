package com.example.bkback.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity @Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String title;
    private String image;
    private String content;
    private Long likedNum;
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account writerAccount;

    @ManyToOne(targetEntity = PostCategory.class, fetch = FetchType.LAZY)
    private PostCategory category;

    @ManyToOne(targetEntity = Clan.class, fetch = FetchType.LAZY)
    private Clan writerClan;

    public Post(String title ,String image, String content, Long likedNum, Account writerAccount, PostCategory category, Clan writerClan) {
        this.title = title;
        this.image = image;
        this.content = content;
        this.likedNum = likedNum;
        this.writerAccount = writerAccount;
        this.category = category;
        this.writerClan = writerClan;
    }

    @OneToMany(mappedBy = "likedPost")
    private Set<LikedPost> likerAccount = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostHashtag> hashtag = new ArrayList<>();
}
