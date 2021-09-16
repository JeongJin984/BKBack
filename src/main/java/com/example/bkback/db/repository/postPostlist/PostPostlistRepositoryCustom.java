package com.example.bkback.db.repository.postPostlist;

import com.example.bkback.db.dto.SimplePostDto;

import java.util.List;

public interface PostPostlistRepositoryCustom {
    List<SimplePostDto> getPostByPostlistTitle(String title);
}
