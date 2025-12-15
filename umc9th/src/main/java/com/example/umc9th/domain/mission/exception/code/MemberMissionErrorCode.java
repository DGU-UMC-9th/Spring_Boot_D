package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION404_1", "존재하지 않는 회원입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION404_2", "존재하지 않는 미션입니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION404_3", "해당 회원의 미션 진행 내역이 없습니다."),
    ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MEMBERMISSION400_1", "이미 도전 중인 미션입니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MEMBERMISSION400_2", "이미 완료된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}