package com.example.umc9th.domain.mypage.service;

import com.example.umc9th.domain.mypage.dto.MyPageSummaryDto;
import com.example.umc9th.domain.mypage.dto.MyReviewItemDto;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    public MyPageSummaryDto getSummary(Long memberId) {
        return memberRepository.findMyPageSummary(memberId);
    }

    public Page<MyReviewItemDto> getMyReviews(Long memberId, Pageable pageable) {
        return reviewRepository.findMyReviews(memberId, pageable);
    }
}