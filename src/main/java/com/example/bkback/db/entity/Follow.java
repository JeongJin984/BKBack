package com.example.bkback.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Follow {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Account follower;
    private String followerName;

    @ManyToOne
    private Account followee;
    private String followeeName;
}
