package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Account;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AccountDto {
    private UUID id;
    private String username;
    private Date birth;
    private String profileImage;

    public AccountDto(UUID id, String username, Date birth, String profileImage) {
        this.id = id;
        this.username = username;
        this.birth = birth;
        this.profileImage = profileImage;
    }
}