package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.HomeMissionResponseDto;
import com.example.umc9th.domain.mission.dto.MissionStatusResponseDto;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;
    private final MemberRepository memberRepository;

    // 진행 중, 진행 완료한 미션 보아서 보는 쿼리
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<List<MissionStatusResponseDto>>> getMissions(
            @PathVariable Long memberId,
            @RequestParam Boolean isComplete,   // true: 완료, false: 진행중
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size // 10개씩 나누어서 페이징
    ) {
        if(!memberRepository.existsById(memberId)) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND);
        }
        List<MissionStatusResponseDto> result =
                missionService.getMissionsByStatus(memberId, isComplete, page, size);
        var sc = MissionSuccessCode.MISSION_LIST_FETCHED;
        String customMessage = result.isEmpty() ? (isComplete ? "완료한 미션이 없습니다" : "진행 중인 미션이 없습니다.") : sc.getMessage();
        return ResponseEntity.status(sc.getStatus())
                .body(new ApiResponse<>(true, sc.getCode(), customMessage, result));
    }

    // 홈 화면 쿼리문
    @GetMapping("/{memberId}/home")
    public ResponseEntity<ApiResponse<List<HomeMissionResponseDto>>> getHomeMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size // 10개씩 나누어서 페이징
    ) {
        if(!memberRepository.existsById(memberId)) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND);
        }
        List<HomeMissionResponseDto> result = missionService.getHomeMissions(memberId, page, size);

        var sc = MissionSuccessCode.HOME_MISSIONS_FETCHED;
        String customMessage = result.isEmpty() ? "미션이 없습니다." : sc.getMessage();
        return ResponseEntity.status(sc.getStatus())
                .body(new ApiResponse<>(true, sc.getCode(), customMessage, result));
    }
}