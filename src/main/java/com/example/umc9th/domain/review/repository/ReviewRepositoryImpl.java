package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewInfo;
import com.example.umc9th.domain.review.dto.ReviewListResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.QReviewPhoto;
import com.example.umc9th.domain.review.entity.QReply;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public ReviewListResponse findMyReviewsWithFilters(
            Long memberId, Long storeId, Integer starRating, Pageable pageable) {

        QReview review = QReview.review;
        QStore store = QStore.store;
        QReviewPhoto reviewPhoto = QReviewPhoto.reviewPhoto;
        QReply reply = QReply.reply;

        // 동적 쿼리 조건 생성
        BooleanBuilder builder = new BooleanBuilder();

        // 내가 작성한 리뷰만
        builder.and(review.userId.eq(memberId));

        // 가게별 필터링
        if (storeId != null) {
            builder.and(review.storeId.eq(storeId));
        }

        // 별점별 필터링
        if (starRating != null) {
            if (starRating == 5) {
                builder.and(review.star.eq(5.0f));
            } else if (starRating >= 1 && starRating <= 4) {
                // 4점대: 4.0 <= star < 5.0
                builder.and(review.star.goe(starRating.floatValue()))
                       .and(review.star.lt(starRating.floatValue() + 1));
            }
        }

        // 메인 쿼리: 리뷰 목록 조회
        List<ReviewInfo> reviews = queryFactory
                .select(Projections.constructor(ReviewInfo.class,
                        review.id,
                        review.content,
                        review.star,
                        review.createdAt,
                        store.name,
                        Expressions.nullExpression(List.class),
                        JPAExpressions
                                .select(reply.count().intValue())
                                .from(reply)
                                .where(reply.reviewId.eq(review.id))
                ))
                .from(review)
                .leftJoin(store).on(review.storeId.eq(store.id))
                .where(builder)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1) // hasNext 확인용 +1
                .fetch();

        // hasNext 확인
        boolean hasNext = reviews.size() > pageable.getPageSize();
        if (hasNext) {
            reviews = reviews.subList(0, pageable.getPageSize());
        }

        // 리뷰 사진 URL 매핑
        if (!reviews.isEmpty()) {
            List<Long> reviewIds = reviews.stream()
                    .map(ReviewInfo::reviewId)
                    .collect(Collectors.toList());

            Map<Long, List<String>> photoMap = queryFactory
                    .select(reviewPhoto.reviewId, reviewPhoto.photoUrl)
                    .from(reviewPhoto)
                    .where(reviewPhoto.reviewId.in(reviewIds))
                    .fetch()
                    .stream()
                    .collect(Collectors.groupingBy(
                            tuple -> tuple.get(reviewPhoto.reviewId),
                            Collectors.mapping(
                                    tuple -> tuple.get(reviewPhoto.photoUrl),
                                    Collectors.toList()
                            )
                    ));

            reviews = reviews.stream()
                    .map(reviewInfo -> new ReviewInfo(
                            reviewInfo.reviewId(),
                            reviewInfo.content(),
                            reviewInfo.star(),
                            reviewInfo.createdAt(),
                            reviewInfo.storeName(),
                            photoMap.getOrDefault(reviewInfo.reviewId(), List.of()),
                            reviewInfo.replyCount()
                    ))
                    .collect(Collectors.toList());
        }

        // 전체 개수 조회
        Long totalCount = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        return new ReviewListResponse(
                reviews,
                totalCount != null ? totalCount.intValue() : 0,
                hasNext
        );
    }
}
