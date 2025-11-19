package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰를 성공적으로 생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
