package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private String content;
    private float star;
}