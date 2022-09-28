package com.jwtLoginTutorial.tutorial.dto;

import com.jwtLoginTutorial.tutorial.entity.Authority;
import com.jwtLoginTutorial.tutorial.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Request 받을 때 쓰는 DTO
 * UsernamePasswordAuthenticationToken를 반환하여 아이디와 비밀번호가 일피하는지 검증하는 로직을 넣을 수 있다
 */
public class MemberRequestDto {
    private String email;
    private String password;
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
