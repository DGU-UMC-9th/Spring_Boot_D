package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewDetailResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewFilterRequest;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService; // 필터링 조회
import com.example.umc9th.domain.review.service.ReviewService; // 리뷰 작성
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;



    @PostMapping("/members/{memberId}")
    public ApiResponse<ReviewResponseDTO> createReview(@PathVariable Long storeId,
                                                       @PathVariable Long memberId,
                                                       @RequestBody ReviewRequestDTO request) {

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewCommandService.createReview(storeId, memberId, request));
    }


    @GetMapping("/my")
    public ApiResponse<Page<ReviewDetailResponseDTO>> getMyFilteredReviews(
            @PathVariable Long storeId,
            @PathVariable Long memberId,
            @RequestParam(required = false) Double rating, // 별점대 필터링 (예: 4.0, 3.0)
            @PageableDefault(size = 10) Pageable pageable) {

        ReviewFilterRequest filterRequest = new ReviewFilterRequest();

        // Request DTO에 필터링 조건 설정
        filterRequest.setStoreId(storeId); // 경로 변수 storeId 사용
        filterRequest.setRating(rating);   // 쿼리 파라미터 rating 사용

        Page<ReviewDetailResponseDTO> response =
                reviewQueryService.getMyFilteredReviews(memberId, filterRequest, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}