package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "MISSION200_1", "미션 목록 조회가 완료되었습니다."),
    HOME_MISSIONS_FETCHED(HttpStatus.OK, "MISSION200_2", "홈 화면 미션 조회가 완료되었습니다."),
    MISSION_CREATED(HttpStatus.CREATED, "MISSION201_1", "미션이 성공적으로 생성되었습니다."),
    MISSION_COMPLETED(HttpStatus.OK, "MISSION200_4", "미션 진행이 성공적으로 완료되었습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}