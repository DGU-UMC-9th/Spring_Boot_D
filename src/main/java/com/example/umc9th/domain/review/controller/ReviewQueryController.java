package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.dto.ReviewSearchResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")
    public List<ReviewSearchResponseDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float star
    ) {
        return reviewQueryService.findMyReviews(memberId, storeName, star);
    }

}