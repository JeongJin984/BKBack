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
public class LikedComment {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private Comment comment;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account likerAccount;

    public LikedComment(Comment comment, Account likerAccount) {
        this.comment = comment;
        this.likerAccount = likerAccount;
    }
}
