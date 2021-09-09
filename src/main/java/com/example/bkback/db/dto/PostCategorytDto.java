package com.example.bkback.db.dto;

import com.example.bkback.db.entity.PostCategory;
import lombok.Data;

@Data
public class PostCategorytDto {
    private String name;

    public PostCategorytDto(String name) {
        this.name = name;
    }

    public PostCategorytDto(PostCategory category) {
        this.name = category.getName();
    }
}
