package com.example.bkback.api;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
public class ResourceController {
    @GetMapping(value = "/image/{imageName}.{extension}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getResource(
            @PathVariable("imageName") String imageName,
            @PathVariable("extension") String extension,
            HttpServletRequest request
    ) throws IOException {
        String imagePath = System.getProperty("catalina.base") + imageName + "." + extension;
        InputStream imageStream = new FileInputStream(imagePath);
        byte[] image = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return image;
    }

    @GetMapping("/haha")
    public String haha() {
        return System.getProperty("catalina.base");
    }
}
