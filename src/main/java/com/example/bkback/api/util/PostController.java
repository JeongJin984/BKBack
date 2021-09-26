package com.example.bkback.api.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class PostController {

    @PostMapping("/upload")
    @ResponseBody
    public void uploadPost(
            @RequestParam
            MultipartFile post) throws IOException {
        byte[] bytes = post.getBytes();
        Files.write(Path.of("outputfile.jpg"), bytes);
    }
}
