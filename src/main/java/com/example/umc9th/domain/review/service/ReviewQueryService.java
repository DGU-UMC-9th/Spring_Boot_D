package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewSearchResponseDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewSearchResponseDto> findMyReviews(Long memberId, String storeName, Float star) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.contains(storeName));
        }

        if (star != null) {
            if (star == 5.0f) {
                builder.and(review.star.eq(5.0f));
            } else {
                builder.and(review.star.goe(star).and(review.star.lt(star + 1.0f)));
            }
        }
        List<Review> reviews = reviewRepository.searchReview(builder);

        return reviews.stream()
                .map(r -> ReviewSearchResponseDto.builder()
                        .id(r.getId())
                        .content(r.getContent())
                        .star(r.getStar())
                        .reply(r.getReply())
                        .build())
                .collect(Collectors.toList());

    }
}