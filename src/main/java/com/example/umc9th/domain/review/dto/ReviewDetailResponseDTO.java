package com.example.umc9th.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewDetailResponseDTO {

    private Long reviewId;
    private String nickname;
    private Double rating;
    private String content;
    private LocalDateTime createdAt;
    private String replyContent;
    private LocalDateTime replyCreatedAt;

    @QueryProjection
    public ReviewDetailResponseDTO(Long reviewId, String nickname, Double rating, String content, LocalDateTime createdAt, String replyContent, LocalDateTime replyCreatedAt) {
        this.reviewId = reviewId;
        this.nickname = nickname;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
        this.replyContent = replyContent;
        this.replyCreatedAt = replyCreatedAt;
    }
}