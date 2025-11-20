package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.res.ReviewSearchResDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public List<ReviewSearchResDto> findMyReviews(Long memberId, String storeName, Float star) {
        if(!memberRepository.existsById(memberId)) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND);
        }
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
                .map(r -> ReviewSearchResDto.builder()
                        .id(r.getId())
                        .content(r.getContent())
                        .star(r.getStar())
                        .reply(r.getReply())
                        .build())
                .collect(Collectors.toList());

    }
}