package com.example.bkback.db.repository.account;

import com.example.bkback.db.dto.AccountDto;
import com.example.bkback.db.dto.AuthenticationDto;
import com.example.bkback.db.dto.ProfileDto;

public interface AccountRepositoryCustom {
    AuthenticationDto findAuthInfoByUsername(String s);

    AccountDto findAccountByUsername(String s);

    ProfileDto getProfile(String s);
}
