package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDetailResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryDsl {
    Page<ReviewDetailResponseDTO> findMyReviewsWithFilter(
            Long memberId,
            ReviewFilterRequest request,
            Pageable pageable);
}
