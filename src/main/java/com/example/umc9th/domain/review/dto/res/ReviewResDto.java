package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResDto {

    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            String content,
            float star,
            Long memberId,
            Long storeId
    ) {}
}