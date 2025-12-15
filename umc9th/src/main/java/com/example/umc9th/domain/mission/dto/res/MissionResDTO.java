package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionResDTO {

    @Builder
    public record CreateMissionDTO(
            Long missionId,
            LocalDateTime deadline,
            String conditional,
            int point,
            Long storeId
    ) {}

    @Builder
    public record StoreMissionDTO(
            Long missionId,
            String conditional,
            int point,
            LocalDateTime deadline,
            Long storeId,
            String storeName
    ) {}

}