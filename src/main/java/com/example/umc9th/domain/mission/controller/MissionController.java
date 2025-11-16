package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.HomeMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
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