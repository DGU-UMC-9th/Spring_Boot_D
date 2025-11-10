package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "리뷰 API", description = "리뷰 조회 관련 API")
public interface ReviewApi {

    @Operation(summary = "내가 작성한 리뷰 조회", description = "가게별, 별점별 필터링이 가능한 리뷰 조회 API")
    ResponseEntity<ReviewListResponse> getMyReviews(
            @Parameter(description = "회원 ID", required = true)
            Long memberId,
            @Parameter(description = "가게 ID (선택)")
            Long storeId,
            @Parameter(description = "별점 (5=5점, 4=4점대, 3=3점대...)")
            Integer starRating,
            @Parameter(description = "페이지 번호 (기본값: 0)")
            Integer page,
            @Parameter(description = "페이지 크기 (기본값: 10)")
            Integer size
    );

    @Operation(summary = "특정 가게의 내 리뷰 조회", description = "특정 가게에 대한 내가 작성한 리뷰만 조회")
    ResponseEntity<ReviewListResponse> getMyReviewsByStore(
            @Parameter(description = "회원 ID", required = true)
            Long memberId,
            @Parameter(description = "가게 ID", required = true)
            Long storeId,
            @Parameter(description = "페이지 번호 (기본값: 0)")
            Integer page,
            @Parameter(description = "페이지 크기 (기본값: 10)")
            Integer size
    );

    @Operation(summary = "특정 별점의 내 리뷰 조회", description = "특정 별점대의 내가 작성한 리뷰만 조회")
    ResponseEntity<ReviewListResponse> getMyReviewsByStar(
            @Parameter(description = "회원 ID", required = true)
            Long memberId,
            @Parameter(description = "별점 (5=5점, 4=4점대, 3=3점대...)", required = true)
            Integer starRating,
            @Parameter(description = "페이지 번호 (기본값: 0)")
            Integer page,
            @Parameter(description = "페이지 크기 (기본값: 10)")
            Integer size
    );
}

