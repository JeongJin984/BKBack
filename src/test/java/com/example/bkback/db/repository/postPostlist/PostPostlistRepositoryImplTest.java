package com.example.bkback.db.repository.postPostlist;

import com.example.bkback.db.dto.SimplePostDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostPostlistRepositoryImplTest {

    @Autowired
    PostPostlistRepository postlistRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws JsonProcessingException {
        List<SimplePostDto> postlist1 = postlistRepository.getPostByPostlistTitle("postlist1");
        System.out.println(mapper.writeValueAsString(postlist1));
    }
}