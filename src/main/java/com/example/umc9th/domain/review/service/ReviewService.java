package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewFilterRequest;
import com.example.umc9th.domain.review.dto.ReviewListResponse;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    
    public ReviewListResponse getMyReviews(
            Long memberId,
            ReviewFilterRequest request) {

        // 페이징 정보 생성
        Pageable pageable = PageRequest.of(request.page(), request.size());

        // QueryDSL을 통한 동적 쿼리 실행
        return reviewRepository.findMyReviewsWithFilters(
                memberId,
                request.storeId(),
                request.starRating(),
                pageable
        );
    }
}
