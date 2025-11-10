package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewListResponse;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    ReviewListResponse findMyReviewsWithFilters(
            Long memberId, 
            Long storeId, 
            Integer starRating, 
            Pageable pageable
    );
}
