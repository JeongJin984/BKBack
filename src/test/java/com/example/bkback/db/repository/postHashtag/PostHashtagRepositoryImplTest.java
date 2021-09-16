package com.example.bkback.db.repository.postHashtag;

import com.example.bkback.db.dto.SimplePostDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostHashtagRepositoryImplTest {

    @Autowired
    PostHashtagRepository postHashtagRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws JsonProcessingException {
        List<SimplePostDto> test1 = postHashtagRepository.getPostByHashtag("test");
        System.out.println(mapper.writeValueAsString(test1));
    }
}