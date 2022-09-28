package com.jwtLoginTutorial.tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 토큰의 값을 헤더에서 뽑거나 헤더에 삽입할때 사용하는 DTO
 */
public class TokenDto {
    private String grantType;
    private String accessToken;
    private Long tokenExpiresIn;
}
