package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

public class CompleteMissionReqDTO {

    @Builder
    public record MissionPreviewDTO(
        Long memberId,
        Long missionId
    ) { }
}
