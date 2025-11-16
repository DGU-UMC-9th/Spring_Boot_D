package com.example.umc9th.global.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾지 못했습니다."),

    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_2",
            "해당 지역을 찾지 못했습니다."),

    DUPLICATED_STORE(HttpStatus.BAD_REQUEST,
            "STORE400_1",
            "이미 존재하는 가게입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}