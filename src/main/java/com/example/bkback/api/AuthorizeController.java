package com.example.bkback.api;

import com.example.bkback.api.util.TokenData;
import com.example.bkback.db.repository.account.AccountRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Objects;

import static com.example.bkback.api.UserController.getGoogleProfile;
import static org.springframework.util.StringUtils.hasText;

@Controller
@RequiredArgsConstructor
public class AuthorizeController {

    private final Environment env;
    private final AccountRepository accountRepository;

    private TokenData getToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", (String) env.getProperty("auth.google.client_id"));
        map.add("client_secret", (String) env.getProperty("auth.google.client_secret"));
        map.add("code", code);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", "http://localhost:8081/auth");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<TokenData> response =
                    restTemplate.postForEntity(
                            "https://oauth2.googleapis.com/token",
                            entity,
                            TokenData.class
                    );
            return response.getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @GetMapping(value = "/auth")
    public String permit(@RequestParam String code, HttpServletResponse servletResponse) throws URISyntaxException {
        String access_token = "";
        try {
            TokenData tokenData = getToken(code);
            access_token =  tokenData.getAccess_token();
            Cookie access_cookie = new Cookie(
                    "access_token",
                    access_token
            );
            Cookie platform_name = new Cookie("platform", "google");

            access_cookie.setPath("/");
            platform_name.setPath("/");

            servletResponse.addCookie(access_cookie);
            servletResponse.addCookie(platform_name);

            String refresh_token = tokenData.getRefresh_token();
            if(refresh_token != null && refresh_token.length() > 3) {
                Cookie refresh_cookie = new Cookie(
                        "refresh_token",
                        tokenData.getRefresh_token()
                );
                refresh_cookie.setPath("/");
                servletResponse.addCookie(refresh_cookie);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        UserController.GoogleProfile googleProfile = getGoogleProfile(access_token);

        if(accountRepository.findAccountByUsername(googleProfile.name) == null) {
            return "redirect:http://localhost:3000/signup?google=" + googleProfile.name;
        } else {
            return "redirect:http://localhost:3000/";
        }
    }
}
