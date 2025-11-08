package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(BooleanBuilder builder);
}