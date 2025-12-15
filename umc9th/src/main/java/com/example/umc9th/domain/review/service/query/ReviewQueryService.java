package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public Page<ReviewResDto.ReviewSearchResDto> findMyReviews(Long memberId, String storeName, Float star, Pageable pageable) {
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
        Page<Review> reviewPage = reviewRepository.searchReview(builder, pageable);

        return reviewPage.map(r ->
                ReviewResDto.ReviewSearchResDto.builder()
                        .id(r.getId())
                        .content(r.getContent())
                        .star(r.getStar())
                        .reply(r.getReply())
                        .build()
        );
    }
}