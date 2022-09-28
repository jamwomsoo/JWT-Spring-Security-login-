package com.jwtLoginTutorial.tutorial.controller;

import com.jwtLoginTutorial.tutorial.dto.ChangePasswordRequestDto;
import com.jwtLoginTutorial.tutorial.dto.MemberRequestDto;
import com.jwtLoginTutorial.tutorial.dto.MemberResponseDto;
import com.jwtLoginTutorial.tutorial.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo(){
        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
        return ResponseEntity.ok(myInfoBySecurity);
    }

    @PostMapping("/nickname")
    public ResponseEntity<MemberResponseDto> setMemberNickname(@RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(memberService.changeMemberNickname(requestDto.getEmail(),requestDto.getNickname() ));
    }

    @PostMapping("/password")
    public ResponseEntity<MemberResponseDto> setMemberPassword(@RequestBody ChangePasswordRequestDto requestDto){
        return ResponseEntity.ok(memberService.changeMemberPassword(requestDto.getEmail(),requestDto.getExPassword(), requestDto.getNewPassword()));
    }
}
