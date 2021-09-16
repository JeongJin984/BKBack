package com.example.bkback.db.repository.postHashtag;

import com.example.bkback.db.dto.SimplePostDto;

import java.util.List;

public interface PostHashtagRepositoryCustom {
    List<SimplePostDto> getPostByHashtag(String hashtagName);
}
