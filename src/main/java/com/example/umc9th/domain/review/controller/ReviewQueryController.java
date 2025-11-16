package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewSearchResDto;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<ReviewSearchResDto>>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float star
    ) {
        List<ReviewSearchResDto> reviews = reviewQueryService.findMyReviews(memberId, storeName, star);
        var sc = ReviewSuccessCode.MY_REVIEWS_FETCHED;
        String customMessage = reviews.isEmpty() ? "작성한 리뷰가 없습니다. " : sc.getMessage();

        return ResponseEntity.status(sc.getStatus())
                .body(new ApiResponse<>(true, sc.getCode(), customMessage, reviews));
    }
}