package com.example.umc9th.domain.mypage.controller;

import com.example.umc9th.domain.mypage.dto.MyPageSummaryDto;
import com.example.umc9th.domain.mypage.dto.MyReviewItemDto;
import com.example.umc9th.domain.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    // 상단 프로필 카드 (닉네임/이메일/휴대폰 인증/포인트)
    @GetMapping("/summary")
    public MyPageSummaryDto summary(@RequestParam Long memberId) {
        return myPageService.getSummary(memberId);
    }

    // 작성한 리뷰 (페이징)
    @GetMapping("/reviews")
    public Page<MyReviewItemDto> myReviews(@RequestParam Long memberId, Pageable pageable) {
        return myPageService.getMyReviews(memberId, pageable);
    }
}