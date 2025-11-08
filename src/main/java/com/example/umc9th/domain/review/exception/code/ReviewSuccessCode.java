package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 작성되었습니다."),
    MY_REVIEWS_FETCHED(HttpStatus.OK, "REVIEW200_1", "내 리뷰 목록 조회가 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}