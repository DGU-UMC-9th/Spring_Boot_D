package com.example.umc9th.domain.mypage.dto;

// MyPageSummaryDto.java
public record MyPageSummaryDto(
        Long memberId,
        String nickname,     // name을 nickname 자리에 담아줌
        String email,
        Boolean phoneVerified,  // primitive 말고 Wrapper 권장
        Integer point
) { }