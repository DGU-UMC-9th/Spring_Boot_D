package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found")); // 실제로 존재하는 회원인지 검사

        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found")); // 실제로 존재하는 가게인지 검사

        Review review = Review.builder()
                .content(requestDto.getContent())
                .star(requestDto.getStar())
                .member(member)
                .store(store)
                .build();
        // 엔티티 생성

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(savedReview.getId(), savedReview.getContent(), savedReview.getStar());
    }
}