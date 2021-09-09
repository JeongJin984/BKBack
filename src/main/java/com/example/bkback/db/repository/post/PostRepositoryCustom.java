package com.example.bkback.db.repository.post;

import com.example.bkback.db.dto.PostDto;
import com.example.bkback.db.dto.SimplePostDto;
import com.example.bkback.db.searchCondition.PostSearchCondition;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostDto> getAllPost(PostSearchCondition condition);
    List<SimplePostDto> getPostListByWriter(PostSearchCondition condition);
}
