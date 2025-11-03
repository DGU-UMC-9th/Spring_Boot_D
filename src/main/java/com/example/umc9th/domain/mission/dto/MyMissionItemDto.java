package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.membermission.enums.MissionStatus;

import java.time.LocalDateTime;

public record MyMissionItemDto(
        Long memberMissionId,
        Long missionId,
        String storeName,
        String conditional,   // 미션 문구
        Integer point,        // 보상 포인트
        MissionStatus status, // IN_PROGRESS / COMPLETED
        LocalDateTime updatedAt
) {}