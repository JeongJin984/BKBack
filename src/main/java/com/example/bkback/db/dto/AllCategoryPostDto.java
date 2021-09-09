package com.example.bkback.db.dto;

import com.example.bkback.db.entity.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllCategoryPostDto {
    private Long id;
    private String categoryName;
    List<PostDto> list = new ArrayList<>();

    public AllCategoryPostDto(Long id, String name, Post post) {

    }
}
