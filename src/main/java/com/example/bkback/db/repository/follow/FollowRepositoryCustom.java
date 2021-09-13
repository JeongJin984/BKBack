package com.example.bkback.db.repository.follow;

import com.example.bkback.db.dto.AccountDto;

import java.util.List;

public interface FollowRepositoryCustom {
    List<AccountDto> getFollowerByUsername(String username);

    List<AccountDto> getFolloweeByUsername(String username);
}
