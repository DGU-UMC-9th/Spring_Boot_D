package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewDetailResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewFilterRequest;
import com.example.umc9th.domain.review.repository.ReviewQueryDsl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewQueryDsl reviewQueryDsl;

    public Page<ReviewDetailResponseDTO> getMyFilteredReviews(
            Long memberId,
            ReviewFilterRequest request,
            Pageable pageable) {

        return reviewQueryDsl.findMyReviewsWithFilter(memberId, request, pageable);
    }
}