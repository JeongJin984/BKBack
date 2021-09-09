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
public class Intimacy {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account accountA;
    private String nameA;
    private Long scoreA;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account accountB;
    private String nameB;
    private Long scoreB;

    public Intimacy(Account accountA, String nameA, Long scoreA, Account accountB, String nameB, Long scoreB) {
        this.accountA = accountA;
        this.nameA = nameA;
        this.scoreA = scoreA;
        this.accountB = accountB;
        this.nameB = nameB;
        this.scoreB = scoreB;
    }
}
