package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    ReviewResponseDTO createReview(Long storeId, Long memberId, ReviewRequestDTO request);
}
