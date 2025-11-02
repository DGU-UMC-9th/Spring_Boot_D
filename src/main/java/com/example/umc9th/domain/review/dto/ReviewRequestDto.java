package com.example.umc9th.domain.review.dto;
import lombok.*;

@Getter
public class ReviewRequestDto {
    private String content;
    private float star;
    private Long memberId;
    private Long storeId;
}