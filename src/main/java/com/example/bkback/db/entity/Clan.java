package com.example.bkback.db.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Clan {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "group")
    private Set<AccountClan> accountId = new HashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<Post> post = new HashSet<>();
}
