package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDetailResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewFilterRequest;
import com.example.umc9th.domain.review.dto.QReviewDetailResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.member.entity.QMember.member;
import static com.example.umc9th.domain.review.entity.QReply.reply;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewDetailResponseDTO> findMyReviewsWithFilter(
            Long memberId,
            ReviewFilterRequest request,
            Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.member.id.eq(memberId));

        if (request.getStoreId() != null) {
            builder.and(storeIdEq(request.getStoreId()));
        }

        if (request.getRating() != null) {
            builder.and(ratingBetween(request.getRating()));
        }

        List<ReviewDetailResponseDTO> content = queryFactory
                .select(new QReviewDetailResponseDTO(
                        review.id,
                        review.member.name,
                        review.rating,
                        review.content,
                        review.createdAt,
                        reply.content,
                        reply.createdAt
                ))
                .from(review)
                .join(review.member, member)
                .leftJoin(reply).on(reply.review.eq(review))
                .where(builder)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }

    private BooleanExpression ratingBetween(Double rating) {
        if (rating == null) {
            return null;
        }
        double nextRating = rating + 1.0;

        // rating은 Double이므로 goe, lt 사용 가능
        return review.rating.goe(rating)
                .and(review.rating.lt(nextRating));
    }
}