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
public class Follow {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account follower;
    private String followerName;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account followee;
    private String followeeName;

    public Follow(Account follower, String followerName, Account followee, String followeeName) {
        this.follower = follower;
        this.followerName = followerName;
        this.followee = followee;
        this.followeeName = followeeName;
    }
}
