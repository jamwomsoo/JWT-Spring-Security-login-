package com.jwtLoginTutorial.tutorial.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 유저 정보가 저장되는 시점에 다루는 클래스
 * Request가 들어오면 JwtFilter의 doFilter에서 저장되는데 거기에 있는 인증정보를 꺼내서, 그 안의 ID를 반환
 */
public class SecurityUtil {
    private SecurityUtil(){}

    public static Long getCurrentMemberId(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null){
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }
        return Long.parseLong(authentication.getName());
    }
}
