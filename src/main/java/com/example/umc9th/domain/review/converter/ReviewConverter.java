package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.Create toCreateDTO(Review review) {
        return ReviewResDTO.Create.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .star(review.getStar())
                .content(review.getContent())
                .build();
    }
}
