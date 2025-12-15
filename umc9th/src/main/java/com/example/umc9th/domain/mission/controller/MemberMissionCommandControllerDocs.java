package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberMissionCommandControllerDocs {

    @Operation(
            summary = "진행 중인 미션 완료 처리 API",
            description = """
                    특정 회원의 진행 중인 미션 하나를 완료 상태로 변경하고,
                    변경 후 해당 회원의 '완료된 미션' 목록을 10개씩 페이지네이션하여 반환합니다.
                    page는 1 이상의 정수여야 합니다."""
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공적으로 미션을 완료 처리하고, 완료된 미션 목록을 조회했습니다."
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "요청 값이 올바르지 않습니다."
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "회원 또는 미션 또는 진행 내역이 존재하지 않습니다."
            )
    })
    ResponseEntity<ApiResponse<List<MemberMissionResDTO.ChallengeListDTO>>> completeMission(
            @Parameter(description = "회원 ID", example = "1")
            Long memberId,

            @Parameter(description = "미션 ID", example = "10")
            Long missionId,

            @Parameter(description = "1 이상의 페이지 번호", example = "1")
            @ValidPage Integer page
    );
}