package com.example.bkback.db.repository.postlist;

import com.example.bkback.db.dto.SimplePostlistDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostlistRepositoryImplTest {
    @Autowired
    PostlistRepository postlistRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws JsonProcessingException {
        List<SimplePostlistDto> postlist = postlistRepository.getPostlistByUsername("JJ NAM");
        System.out.println(mapper.writeValueAsString(postlist));
    }

}