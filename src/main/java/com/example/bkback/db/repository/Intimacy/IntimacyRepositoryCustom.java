package com.example.bkback.db.repository.Intimacy;

import com.example.bkback.db.dto.AccountDto;

import java.util.List;

public interface IntimacyRepositoryCustom {
    List<AccountDto> getFriendByUsername(String username);
}
