package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.MyReviewItemDto;
import com.example.umc9th.domain.review.service.MyReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class MyReviewController {

    private final MyReviewService myReviewService;

    @GetMapping("/my/review")
    public Page<MyReviewItemDto> myReviews(@RequestParam Long memberId,
                                           @RequestParam(required = false) Long storeId,
                                           @RequestParam(required = false) String storeName,
                                           @RequestParam(required = false) Integer starBand,
                                           Pageable pageable) {
        return myReviewService.getMyReviews(memberId, storeId, storeName, starBand, pageable);
    }
}