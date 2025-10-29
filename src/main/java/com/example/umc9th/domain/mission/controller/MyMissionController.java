package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.membermission.enums.MissionStatus;
import com.example.umc9th.domain.mission.dto.MyMissionItemDto;
import com.example.umc9th.domain.mission.service.MyMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions/my")
public class MyMissionController {
    private final MyMissionService myMissionService;

    // ex) /api/missions/me?status=IN_PROGRESS&page=0&size=10
    @GetMapping("/me")
    public Page<MyMissionItemDto> listMyMissions(@RequestParam Long memberId,
                                                 @RequestParam MissionStatus status,
                                                 Pageable pageable) {
        return myMissionService.getMyMissions(memberId, status, pageable);
    }
}