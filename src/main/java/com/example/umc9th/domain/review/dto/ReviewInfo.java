package com.example.umc9th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewInfo(Long reviewId,
                        String content,
                        Float star,
                        LocalDateTime createdAt,
                        String storeName,
                        List<String> photoUrls,
                        Integer replyCount) {
}

