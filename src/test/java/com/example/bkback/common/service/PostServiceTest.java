package com.example.bkback.common.service;

import com.example.bkback.db.dto.PostDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void test1() throws JsonProcessingException {
        PostDto postWithComment = postService.getPostWithComment(UUID.fromString("6d2c1483-f764-4d3e-a45e-c31779b67be4"));
        System.out.println(mapper.writeValueAsString(postWithComment));
    }
}