package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResDto>> createReview(@RequestBody ReviewReqDto requestDto) {
        ReviewResDto responseDto = reviewService.createReview(requestDto);
        var sc = ReviewSuccessCode.REVIEW_CREATED;
        return ResponseEntity.status(sc.getStatus())
                .body(ApiResponse.onSuccess(sc, responseDto));
    }
}