package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Postlist;
import lombok.Data;

import java.util.UUID;

@Data
public class SimplePostlistDto {
    UUID id;
    String title;

    public SimplePostlistDto(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public SimplePostlistDto(Postlist postlist) {
        this.id = postlist.getId();
        this.title = postlist.getName();
    }
}
