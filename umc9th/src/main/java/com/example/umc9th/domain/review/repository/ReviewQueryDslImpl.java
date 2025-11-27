package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public Page<Review> searchReview(BooleanBuilder builder, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        long totalCount = total != null ? total : 0L;

        List<Review> content = queryFactory
                .selectFrom(review)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, totalCount);
    }
}