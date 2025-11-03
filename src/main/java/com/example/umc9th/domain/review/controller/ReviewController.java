package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> create(@RequestBody CreateReviewReq req) {
        Long id = reviewService.createReview(req.memberId(), req.storeId(), req.star(), req.content());
        return ResponseEntity.ok(Map.of("reviewId", id));
    }

    public record CreateReviewReq(Long memberId, Long storeId, float star, String content) {}
}