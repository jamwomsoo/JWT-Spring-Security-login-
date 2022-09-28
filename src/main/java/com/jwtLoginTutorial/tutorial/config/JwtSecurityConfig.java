package com.jwtLoginTutorial.tutorial.config;


import com.jwtLoginTutorial.tutorial.jwt.JwtFilter;
import com.jwtLoginTutorial.tutorial.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> 인터페이스 구현체
 * 직접만든 TokenProvider와 JwtFilter를 SecurityConfig에 적용할 때 사용
 */
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;

    /**
     * TokenProvider를 주입받아서 JwtFilter를 통해 SecurityConfig안에 필터를 등록하게 되고, 스프링 시큐리티 전반적인 필터에 적용됨
     * @param http
     */
    @Override
    public void configure(HttpSecurity http){
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
