package com.example.bkback.db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDto {
    String username;
    String password;
}
