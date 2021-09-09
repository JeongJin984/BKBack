package com.example.bkback.db.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class ProfileDto {
    private UUID id;
    private String username;
    private Date birth;
    private String profileImage;

    private List<SimplePostDto> writePostAsAccount;
    private List<SimplePostDto> writePostAsClan;

    public ProfileDto(List<SimplePostDto> writePostAsAccount, List<SimplePostDto> writePostAsClan) {
        this.writePostAsAccount = writePostAsAccount;
        this.writePostAsClan = writePostAsClan;
    }

    public ProfileDto(UUID id, String username, Date birth, String profileImage) {
        this.id = id;
        this.username = username;
        this.birth = birth;
        this.profileImage = profileImage;
    }
}
