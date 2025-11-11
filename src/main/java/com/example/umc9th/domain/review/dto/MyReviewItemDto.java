package com.example.umc9th.domain.review.dto;

import java.time.LocalDateTime;

public record MyReviewItemDto(
        Long id,
        String content,
        Float star,
        String reply,          // 사장님 답글 없으면 null
        Long storeId,
        String storeName,
        LocalDateTime createdAt
) {}