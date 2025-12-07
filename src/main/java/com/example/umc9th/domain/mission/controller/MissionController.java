package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.*;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    @Operation(
            summary = "미션 완료 변경 API By 누리 ",
            description = "진행 중인 미션을 진행완료 상태로 변경합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/members")
    public ApiResponse<CompleteMissionResDTO.MissionPreviewListDTO> completeMission(
            @RequestBody CompleteMissionReqDTO.MissionPreviewDTO dto,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETED, memberMissionCommandService.completeMission(dto.memberId(),dto.missionId(),page) );
    }

    @Operation(
            summary = "가게의 미션 목록 조회 API By 누리 ",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/stores")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getStoreMissions(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND,
                missionQueryService.findStoreMissions(storeName, page));
    }

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API By 누리 ",
            description = "내가 진행중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/members")
    public ApiResponse<OngoingMissionResDTO.MissionPreviewListDTO> getMemberMissions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND,
                missionQueryService.findMemberMissions(memberId, page));
    }


    @PostMapping( "/{missionId}/members/{memberId}/challenge")
    public ApiResponse<ChallengeMissionResponseDTO> challengeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATED, missionCommandService.challengeMission(memberId, missionId));
    }

    @GetMapping("/home")
    public ApiResponse<HomeMissionResponseDTO> getHomeMissions(
            @PathVariable Long memberId,
            @PathVariable Long regionId,
            @PageableDefault(size = 5) Pageable pageable) {

        HomeMissionResponseDTO response =
                missionService.getHomeMissions(memberId, regionId , pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }


    @GetMapping("/ongoing")
    public ApiResponse<Page<MissionResponseDTO>> getOngoingMissions(
            @PathVariable Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MissionResponseDTO> response =
                missionService.getOngoingMissions(memberId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }


    @GetMapping("/completed")
    public ApiResponse<Page<MissionResponseDTO>> getCompletedMissions(
            @PathVariable Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MissionResponseDTO> response =
                missionService.getCompletedMissions(memberId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}