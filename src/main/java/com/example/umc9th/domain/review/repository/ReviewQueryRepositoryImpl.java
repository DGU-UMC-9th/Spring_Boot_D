package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.MyReviewItemDto;
import com.example.umc9th.domain.review.entity.QReply;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import com.querydsl.jpa.JPAExpressions;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<MyReviewItemDto> findMyReviews(Long memberId,
                                               Long storeId,
                                               String storeName,
                                               Integer starBand,
                                               Pageable pageable) {

        QReview review = QReview.review;
        QReply reply = QReply.reply;
        QStore store = QStore.store;

        BooleanBuilder where = new BooleanBuilder()
                .and(review.member.id.eq(memberId));

        if (storeId != null) {
            where.and(review.store.id.eq(storeId));
        }
        if (storeName != null && !storeName.isBlank()) {
            where.and(review.store.name.containsIgnoreCase(storeName));
        }
        if (starBand != null) {
            // 5 -> [5.0, 5.0], 4 -> [4.0,5.0), 3 -> [3.0,4.0) …
            if (starBand == 5) {
                where.and(review.star.goe(5.0f));
            } else if (starBand >= 1 && starBand <= 4) {
                float from = starBand.floatValue();
                float to = from + 1.0f;
                where.and(review.star.goe(from).and(review.star.lt(to)));
            }
        }

        var contentQuery = query
                .select(Projections.constructor(
                        MyReviewItemDto.class,
                        review.id,
                        review.content,
                        review.star,
                        JPAExpressions.select(reply.content)         // <- 최신 답글 내용
                                .from(reply)
                                .where(reply.review.eq(review))
                                .orderBy(reply.createdAt.desc())
                                .limit(1),
                        store.id,
                        store.name,
                        review.createdAt
                ))
                .from(review)
                .leftJoin(review.store, store)                       // 가게 정보만 조인
                .where(where)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<MyReviewItemDto> content = contentQuery.fetch();

        var countQuery = query
                .select(review.id.count())
                .from(review)
                .where(where);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}