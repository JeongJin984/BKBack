package com.example.bkback.api.util;

import lombok.Data;

@Data
public class TokenData {
    private String access_token;
    private String refresh_token;
    private Integer expires_in;
    private String scope;
    private String token_type;
}
