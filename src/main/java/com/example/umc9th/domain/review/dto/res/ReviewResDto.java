package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResDto {
    private Long reviewId;
    private String content;
    private float star;
}