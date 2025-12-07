package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.res.MyReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MyReviewConverter {

    public static MyReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ) {
        return MyReviewResDTO.ReviewPreviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(MyReviewConverter::toReviewPreviewDTO)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MyReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ) {
        return MyReviewResDTO.ReviewPreViewDTO.builder()
                .storeName(review.getStore().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

}
