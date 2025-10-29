package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.HomeMissionItemDto;
import com.example.umc9th.domain.mission.service.HomeMissionService;
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

    @GetMapping("/home")
    public Page<HomeMissionItemDto> getHomeMissions(
            @RequestParam Long memberId,
            @RequestParam Long locationId,
            Pageable pageable
    ) {
        return homeMissionService.getHomeMissions(memberId, locationId, pageable);
    }
}