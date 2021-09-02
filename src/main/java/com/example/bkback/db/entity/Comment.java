package com.example.bkback.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    Post post;

    String writer;
}
