package org.example.mf.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {

    public static Cookie getCookie(HttpServletRequest request, final String name) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return null;
        }//end fi

        for(Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }//end if
        }//end for

        return null;

    }

}
