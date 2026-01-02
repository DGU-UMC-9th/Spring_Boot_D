package com.example.umc9th.global.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class MemberReqDTO {

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
