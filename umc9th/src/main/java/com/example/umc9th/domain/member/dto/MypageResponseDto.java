package com.example.umc9th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageResponseDto {
    private String name;       // 닉네임 대신 name 사용
    private String email;
    private String phoneStatus; // 인증 / 미인증
    private int point;
}