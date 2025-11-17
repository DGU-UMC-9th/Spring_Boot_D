package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.HomeMissionItemDto;
import com.example.umc9th.domain.mission.service.HomeMissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions/home")
public class MissionController {

    private final HomeMissionService homeMissionService;

    @GetMapping //http://localhost:8080/api/missions/home?memberId=1&locationId=1
    public ApiResponse<Page<HomeMissionItemDto>> getHomeMissions(
            @RequestParam Long memberId,
            @RequestParam Long locationId,
            Pageable pageable
    ) {
        Page<HomeMissionItemDto> missions =
                homeMissionService.getHomeMissions(memberId, locationId, pageable);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missions
        );
    }
}