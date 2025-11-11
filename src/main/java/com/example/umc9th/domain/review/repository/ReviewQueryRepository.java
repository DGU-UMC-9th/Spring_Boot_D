package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.MyReviewItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    /**
     * 내가 작성한 리뷰 조회 (단일 API)
     * @param memberId  필수: 본인
     * @param storeId   선택: 특정 가게만
     * @param storeName 선택: 가게명으로 필터(부분일치)
     * @param starBand  선택: 5,4,3,2,1 (별점대). null이면 전체
     */
    Page<MyReviewItemDto> findMyReviews(Long memberId,
                                        Long storeId,
                                        String storeName,
                                        Integer starBand,
                                        Pageable pageable);
}