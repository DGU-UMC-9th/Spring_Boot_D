package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Validated
public class ReviewQueryController implements ReviewQueryControllerDocs{

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<Page<ReviewResDto.ReviewSearchResDto>>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float star,
            @RequestParam Integer page
    ) {

        var pageable = PageRequest.of(page-1, 10);
        Page<ReviewResDto.ReviewSearchResDto> reviews = reviewQueryService.findMyReviews(
                memberId,
                storeName,
                star,
                pageable
        );
        var sc = ReviewSuccessCode.MY_REVIEWS_FETCHED;
        String customMessage = reviews.isEmpty() ? "작성한 리뷰가 없습니다. " : sc.getMessage();

        return ResponseEntity.status(sc.getStatus())
                .body(ApiResponse.onSuccess(
                        sc,
                        reviews
                ));
    }
}