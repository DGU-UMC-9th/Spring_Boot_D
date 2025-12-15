package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;

public interface ReviewCommandService {
    ReviewResDto.CreateReviewDTO createReview(
            Long memberId,
            Long storeId,
            ReviewReqDto.CreateReviewDTO dto
    );
}
