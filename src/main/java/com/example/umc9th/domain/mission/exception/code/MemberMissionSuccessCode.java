package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    CHALLENGE_STARTED(HttpStatus.CREATED, "MM201_1", "미션 도전을 시작했습니다."),
    CHALLENGE_COMPLETED(HttpStatus.OK, "MM200_1", "미션을 완료했습니다."),
    CHALLENGES_FETCHED(HttpStatus.OK, "MM200_2", "도전 중인 미션 목록 조회가 완료되었습니다.");

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}