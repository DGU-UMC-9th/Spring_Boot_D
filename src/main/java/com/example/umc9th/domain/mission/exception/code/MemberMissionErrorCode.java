package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MemberMISSION404_1",
            "해당 미션을 도전하지 않았습니다."),
    MISSION_STATUS_NOT_CHANGEABLE(HttpStatus.BAD_REQUEST, "MemberMISSION400_1",
            "이미 완료된 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
