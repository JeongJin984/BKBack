package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Clan;
import lombok.Data;

import java.util.UUID;

@Data
public class ClanDto {

    private UUID id;
    private String name;
    private String master;

    public ClanDto(UUID id, String name, String master) {
        this.id = id;
        this.name = name;
        this.master = master;
    }

    public ClanDto(Clan clan) {
        this.id = clan.getId();
        this.name = clan.getName();
        this.master = clan.getMaster();
    }
}
