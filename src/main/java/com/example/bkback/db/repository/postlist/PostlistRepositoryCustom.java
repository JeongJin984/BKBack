package com.example.bkback.db.repository.postlist;

import com.example.bkback.db.dto.SimplePostlistDto;

import java.util.List;

public interface PostlistRepositoryCustom {
    List<SimplePostlistDto> getPostlistByUsername(String username);
}
