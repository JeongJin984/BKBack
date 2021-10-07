package com.example.bkback.api;

import com.example.bkback.common.service.PostService;
import com.example.bkback.db.dto.PostDto;
import com.example.bkback.db.entity.Account;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;

    @GetMapping("/posts")
    @ResponseBody
    public List<PostDto> getPosts() {
        return postService.getAllPost();
    }

    @GetMapping("/fullpost")
    @ResponseBody
    public PostDto getPosts(@RequestParam String id) {
        return postService.getPostWithComment(UUID.fromString(id));
    }

    @PostMapping("/upload")
    @ResponseBody
    public PostDto uploadPost(
            @RequestParam("File") MultipartFile post,
            @RequestParam("writer") String writerId
    ) throws IOException {
        String new_file_name = Long.toString(System.nanoTime()) + ".jpg";
        Path path = Paths.get(System.getProperty("catalina.base") + new_file_name);
        Files.write(path, post.getBytes());
        return postService.uploadPost(new_file_name, writerId);
    }

    @PostMapping("/post/like")
    @ResponseBody
    public void likePost(@RequestBody LikePostVO likePost) {
        postService.likePost(likePost.userId, likePost.postId);
    }

    @PostMapping("/post/unlike")
    @ResponseBody
    public void unlikePost(@RequestBody LikePostVO likePost) {
        postService.unlikePost(likePost.userId, likePost.postId);
    }

    @Data
    static class LikePostVO {
        String userId;
        String postId;
    }
}
