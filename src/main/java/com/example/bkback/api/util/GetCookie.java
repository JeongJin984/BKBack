package com.example.bkback.api.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetCookie {
    public static String get(HttpServletRequest request, String key) {
        for(Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return "";
    }
}
