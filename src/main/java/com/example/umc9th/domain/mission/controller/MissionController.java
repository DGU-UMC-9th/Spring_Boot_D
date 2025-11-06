package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.HomeMissionResponseDto;
import com.example.umc9th.domain.mission.dto.MissionStatusResponseDto;
import com.example.umc9th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    // 진행 중, 진행 완료한 미션 보아서 보는 쿼리
    @GetMapping("/{memberId}")
    public ResponseEntity<List<MissionStatusResponseDto>> getMissions(
            @PathVariable Long memberId,
            @RequestParam Boolean isComplete,   // true: 완료, false: 진행중
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size // 10개씩 나누어서 페이징
    ) {
        List<MissionStatusResponseDto> result =
                missionService.getMissionsByStatus(memberId, isComplete, page, size);
        return ResponseEntity.ok(result);
    }

    // 홈 화면 쿼리문
    @GetMapping("/{memberId}/home")
    public ResponseEntity<List<HomeMissionResponseDto>> getHomeMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size // 10개씩 나누어서 페이징
    ) {
        List<HomeMissionResponseDto> result = missionService.getHomeMissions(memberId, page, size);
        return ResponseEntity.ok(result);
    }
}