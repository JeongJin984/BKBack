package com.example.bkback.api;

import com.example.bkback.api.util.GetCookie;
import com.example.bkback.db.dto.UserDto;
import com.example.bkback.security.util.jwt.GetTokenInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@RestController
public class UserController {

    @GetMapping(value = "/user")
    public UserDto authorize(HttpServletRequest request) throws IOException {
        String access_token = GetCookie.get(request, "access_token");
        String platform = GetCookie.get(request, "platform");

        UserDto user = new UserDto();
        assert platform != null;
        if(platform.equals("google")) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                assert access_token != null;
                headers.setBearerAuth(access_token);
                HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(new LinkedMultiValueMap<>(),headers);
                ResponseEntity<GoogleProfile> response = restTemplate.exchange(
                        "https://www.googleapis.com/userinfo/v2/me",
                        HttpMethod.GET,
                        entity,
                        GoogleProfile.class
                );
                user.setName(Objects.requireNonNull(response.getBody()).name);
            } catch (Exception e) {

            }
        } else if(platform.equals("local")) {
            user.setName(GetTokenInfo.getUserName(access_token));
        }
        return user;
    }

    @Getter @Setter
    static class GoogleProfile {
        String family_name;
        String name;
    }
}
