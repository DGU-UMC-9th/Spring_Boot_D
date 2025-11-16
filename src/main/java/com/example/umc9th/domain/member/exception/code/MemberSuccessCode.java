package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MYPAGE_FETCHED(HttpStatus.OK, "MEMBER200_1", "마이페이지 조회가 완료되었습니다."),
    FOUND(HttpStatus.OK,
            "MEMBER200_1",
                    "성공적으로 사용자를 조회했습니다."),
            ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}