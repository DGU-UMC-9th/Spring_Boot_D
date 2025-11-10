package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewFilterRequest;
import com.example.umc9th.domain.review.dto.ReviewListResponse;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewService reviewService;

    @Override
    @GetMapping("/my")
    public ResponseEntity<ReviewListResponse> getMyReviews(
            @RequestHeader("X-Member-Id") Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer starRating,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        ReviewFilterRequest request = 
                new ReviewFilterRequest(storeId, starRating, page, size);

        ReviewListResponse response = 
                reviewService.getMyReviews(memberId, request);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/my/store/{storeId}")
    public ResponseEntity<ReviewListResponse> getMyReviewsByStore(
            @RequestHeader("X-Member-Id") Long memberId,
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        ReviewFilterRequest request = 
                new ReviewFilterRequest(storeId, null, page, size);

        ReviewListResponse response = 
                reviewService.getMyReviews(memberId, request);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/my/star/{starRating}")
    public ResponseEntity<ReviewListResponse> getMyReviewsByStar(
            @RequestHeader("X-Member-Id") Long memberId,
            @PathVariable Integer starRating,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        ReviewFilterRequest request = 
                new ReviewFilterRequest(null, starRating, page, size);

        ReviewListResponse response = 
                reviewService.getMyReviews(memberId, request);

        return ResponseEntity.ok(response);
    }
}
