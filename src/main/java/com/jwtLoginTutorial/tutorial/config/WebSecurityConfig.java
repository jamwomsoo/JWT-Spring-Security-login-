package com.jwtLoginTutorial.tutorial.config;

import com.jwtLoginTutorial.tutorial.jwt.JwtAccessDeniedHandler;
import com.jwtLoginTutorial.tutorial.jwt.JwtAuthenticationEntryPoint;
import com.jwtLoginTutorial.tutorial.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    /**
     * request로부터 받은 비밀번호를 암호화하기 위한 메소드
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .httpBasic().disable()                    //https만 사용
                .csrf().disable()                         // csrf 방지 막음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Rest api를 통해 세션 없이 토큰을 주고 받기 때문에 Stateless 설정

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()  // 여기가 로그인 페이지
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));     //JwtSecurityConfig 클래스를 통해 tokenprovider를 적용시킴

        return http.build();
    }
}
