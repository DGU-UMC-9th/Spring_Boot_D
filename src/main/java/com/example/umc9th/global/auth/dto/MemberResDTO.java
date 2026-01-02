package com.example.umc9th.global.auth.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
