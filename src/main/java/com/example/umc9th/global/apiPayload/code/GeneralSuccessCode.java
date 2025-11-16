package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 요청을 처리했습니다."),

    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "리소스를 성공적으로 생성했습니다."),

    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202_1",
            "요청이 성공적으로 접수되었습니다."),

    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204_1",
            "요청에 성공했으며, 응답할 내용이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}