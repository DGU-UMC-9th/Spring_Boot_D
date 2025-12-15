package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/members/{memberId}/stores/{storeId}")
    public ApiResponse<ReviewResDto.CreateReviewDTO> createReview(
            @PathVariable Long memberId,
            @PathVariable Long storeId,
            @RequestBody ReviewReqDto.CreateReviewDTO dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, reviewCommandService.createReview(memberId, storeId, dto));
    }
}