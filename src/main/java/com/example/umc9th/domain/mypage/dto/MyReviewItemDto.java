package com.example.umc9th.domain.mypage.dto;

import java.time.LocalDateTime;

public record MyReviewItemDto(
        Long reviewId,
        String storeName,
        Float star,
        String content,
        LocalDateTime createdAt
) { }