package com.example.bkback.api;

import com.example.bkback.api.util.EmailUtil;
import com.example.bkback.api.util.GetCookie;
import com.example.bkback.api.util.TokenData;
import com.example.bkback.common.service.AccountService;
import com.example.bkback.db.dto.AccountDto;
import com.example.bkback.db.dto.ProfileDto;
import com.example.bkback.db.entity.Account;
import com.example.bkback.db.repository.account.AccountRepository;
import com.example.bkback.security.util.jwt.GetTokenInfo;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    private final EmailUtil emailUtil;

    @PostMapping("/signup")
    @ResponseBody
    public void signup(
            @RequestParam("username") String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String birth,
            @RequestParam MultipartFile profile
            ) throws IOException, ParseException {
        String new_file_name = Long.toString(System.nanoTime()) + ".jpg";
        Path path = Paths.get(System.getProperty("catalina.base") + new_file_name);
        Files.write(path, profile.getBytes());

        Account account = new Account(username, passwordEncoder.encode(password), email, new SimpleDateFormat("yyyy-MM-dd").parse(birth), new_file_name);
        accountRepository.saveAndFlush(account);
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public AccountDto authorize(HttpServletRequest request, HttpServletResponse servletResponse) throws IOException {
        String access_token = GetCookie.get(request, "access_token");
        String platform = GetCookie.get(request, "platform");

        if(!(hasText(access_token) && hasText(platform))) {
            return null;
        }

        String username = "";
        if(platform.equals("google")) {
            try {
                GoogleProfile googleProfile = getGoogleProfile(access_token);
                username = googleProfile.name;
            }  catch (HttpClientErrorException e) {
                TokenData tokenData = refreshToken(GetCookie.get(request, "refresh_token"));
                access_token = tokenData.getAccess_token();
                GoogleProfile googleProfile = getGoogleProfile(access_token);
                Cookie access_cookie = new Cookie(
                        "access_token",
                        access_token
                );
                access_cookie.setPath("/");
                servletResponse.addCookie(access_cookie);
                username = googleProfile.name;
            } catch (Exception e) {
                servletResponse.setHeader("Location", "localhost:3000/login");
                servletResponse.setStatus(302);
            }
        } else if(platform.equals("local")) {
            try {
                username = GetTokenInfo.getUserName(access_token);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return accountRepository.findAccountByUsername(username);
    }

    @GetMapping("/profile")
    @ResponseBody
    public ProfileDto getProfile(@RequestParam String username) {
        return accountService.getProfileByUsername(username);
    }

    @PostMapping("/email")
    @ResponseBody
    public String authEmail(@RequestBody Email data) {
        String email = data.getEmail();
        int random = (int)(Math.random() * 10000);
        emailUtil.sendEmail(email, "Peniyo 인증메일 입니다.", Integer.toString(random));
        return Integer.toString(random);
    }

    private TokenData refreshToken(String refresh_token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", (String) env.getProperty("auth.google.client_id"));
        map.add("client_secret", (String) env.getProperty("auth.google.client_secret"));
        map.add("refresh_token", refresh_token);
        map.add("grant_type", "refresh_token");
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

    public static GoogleProfile getGoogleProfile(String access_token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        assert access_token != null;
        headers.setBearerAuth(access_token);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(new LinkedMultiValueMap<>(),headers);
        try {
            ResponseEntity<GoogleProfile> response = restTemplate.exchange(
                    "https://www.googleapis.com/userinfo/v2/me",
                    HttpMethod.GET,
                    entity,
                    GoogleProfile.class
            );
            return response.getBody();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Getter @Setter
    static class GoogleProfile {
        String family_name;
        String name;
    }

    @Getter @Setter
    static class SignUpVO {
        private String username;
        private String password;
        private Date birth;
        private String profileImage;
    }

    @Data
    static class Email {
        private String email;
    }
}
