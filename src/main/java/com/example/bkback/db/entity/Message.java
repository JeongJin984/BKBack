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
public class Message {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String content;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account account;

    public Message(String content, Account account) {
        this.content = content;
        this.account = account;
    }
}
