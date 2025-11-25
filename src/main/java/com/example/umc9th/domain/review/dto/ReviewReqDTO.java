package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewReqDTO {

    @Getter
    @NoArgsConstructor
    public static class Create {

        @Min(1)
        @Max(5)
        private Integer star;     // DTO도 star로 통일!

        @NotBlank
        private String content;
    }
}