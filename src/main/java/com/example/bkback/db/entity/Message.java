package com.example.bkback.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {

    @Id @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    private Account account;


}
