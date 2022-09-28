package com.jwtLoginTutorial.tutorial.service;

import com.jwtLoginTutorial.tutorial.config.SecurityUtil;
import com.jwtLoginTutorial.tutorial.dto.MemberResponseDto;
import com.jwtLoginTutorial.tutorial.entity.Member;
import com.jwtLoginTutorial.tutorial.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 헤더에 있는 token 값을 토대로 Member의 data를 건내주는 메소드
     * @return
     */
    public MemberResponseDto getMyInfoBySecurity(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 닉네임 변경 메소드
     * @param email
     * @param nickname
     * @return
     */
    @Transactional
    public MemberResponseDto changeMemberNickname(String email, String nickname){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("로그인 유정 정보가 없습니다."));
        member.setNickname(nickname);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    /**
     * 패스워드 변경 메소드
     * token값을 토대로 찾은 Member를 통해서 예전 패스워드와 DB의 데이터와 비교
     * @param email
     * @param exPassword
     * @param newPassword
     * @return
     */
    @Transactional
    public MemberResponseDto changeMemberPassword(String email, String exPassword, String newPassword){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        if(!passwordEncoder.matches(exPassword, member.getPassword())){
            throw new RuntimeException("비밀번호가 맞지 않습니다.");
        }
        member.setPassword(passwordEncoder.encode((newPassword)));
        return MemberResponseDto.of(memberRepository.save(member));
    }
}
