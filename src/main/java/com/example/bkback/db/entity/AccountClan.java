package com.example.bkback.db.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class AccountClan {
    @Id @GeneratedValue
    private Long id;

    private Long username;
    private Boolean master;

    @ManyToOne
    Account account;

    @ManyToOne
    Clan group;

}
