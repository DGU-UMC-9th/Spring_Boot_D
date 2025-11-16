package com.example.umc9th.global.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "STORE201_1",
            "가게가 성공적으로 생성되었습니다."),

    FOUND(HttpStatus.OK,
            "STORE200_1",
            "가게 정보를 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}