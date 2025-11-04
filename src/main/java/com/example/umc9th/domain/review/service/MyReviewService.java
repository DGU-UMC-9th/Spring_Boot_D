package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.MyReviewItemDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyReviewService {

    private final ReviewRepository reviewRepository;

    public Page<MyReviewItemDto> getMyReviews(Long memberId,
                                              Long storeId,
                                              String storeName,
                                              Integer starBand,
                                              Pageable pageable) {
        return reviewRepository.findMyReviews(memberId, storeId, storeName, starBand, pageable);
    }
}