package com.jwtLoginTutorial.tutorial.controller;

import com.jwtLoginTutorial.tutorial.dto.MemberRequestDto;
import com.jwtLoginTutorial.tutorial.dto.MemberResponseDto;
import com.jwtLoginTutorial.tutorial.dto.TokenDto;
import com.jwtLoginTutorial.tutorial.repository.MemberRepository;
import com.jwtLoginTutorial.tutorial.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> singup(@RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(authService.singup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(authService.login(requestDto));
    }

}
