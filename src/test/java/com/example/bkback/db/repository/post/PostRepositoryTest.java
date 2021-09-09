package com.example.bkback.db.repository.post;

import com.example.bkback.db.dto.PostDto;
import com.example.bkback.db.dto.ProfileDto;
import com.example.bkback.db.dto.SimplePostDto;
import com.example.bkback.db.entity.Post;
import com.example.bkback.db.searchCondition.PostSearchCondition;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws JsonProcessingException {
        PostSearchCondition condition = new PostSearchCondition();
        condition.setCategory("category");
        List<PostDto> allPost = postRepository.getAllPost(condition);
        System.out.println(mapper.writeValueAsString(allPost));
    }

    @Test
    public void test2() throws JsonProcessingException {
        String username = "JJ NAM";

        PostSearchCondition condition = new PostSearchCondition();
        condition.setWriterClan(username);
        List<SimplePostDto> postList = postRepository.getPostListByWriter(condition);
    }
}