package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<Long> create(@RequestBody CreateReviewReq req) {

        // memberId는 받지 않고 서비스에서 하드코딩함
        Long reviewId = reviewService.createReview(
                req.storeId(),
                req.star(),
                req.content()
        );

        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                reviewId
        );
    }

    // DTO: memberId 제거
    public record CreateReviewReq(Long storeId, float star, String content) {}
}
