package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto responseDto = reviewService.createReview(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}