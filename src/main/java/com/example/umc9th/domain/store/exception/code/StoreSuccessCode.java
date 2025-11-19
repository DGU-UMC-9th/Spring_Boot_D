package com.example.umc9th.domain.store.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.CREATED,
            "STORE201_1",
            "가게를 성공적으로 생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
