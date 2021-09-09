package com.example.bkback.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Clan {
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    private String master;

    public Clan(String name, String master) {
        this.name = name;
        this.master = master;
    }

    @OneToMany(mappedBy = "clan")
    private Set<AccountClan> accountId = new HashSet<>();

    @OneToMany(mappedBy = "writerClan")
    private Set<Post> post = new HashSet<>();


}
