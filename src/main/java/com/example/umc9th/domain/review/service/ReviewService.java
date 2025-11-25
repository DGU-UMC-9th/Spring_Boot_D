package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public Long createReview(Long storeId, float star, String content) {

        // 로그인 미구현 → 과제 요구사항: DB에 있는 유저 한 명 하드코딩
        Long hardCodedMemberId = 1L;

        Member member = memberRepository.findById(hardCodedMemberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(star)
                .content(content)
                .build();

        return reviewRepository.save(review).getId();
    }
}
