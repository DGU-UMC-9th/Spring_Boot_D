package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

public class ReviewConverter {

    public static ReviewResDto.CreateReviewDTO toCreateReviewDTO(Review review) {
        return ReviewResDto.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .memberId(review.getMember().getId())
                .storeId(review.getStore().getId())
                .build();
    }

    public static Review toReview(
            ReviewReqDto.CreateReviewDTO dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .content(dto.content())
                .star(dto.star())
                .member(member)
                .store(store)
                .build();
    }
}