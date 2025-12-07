package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class OngoingMissionResDTO {

    @Builder
    public record MissionPreviewListDTO(
            List<OngoingMissionResDTO.MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) { }
    @Builder
    public record MissionPreviewDTO(
            String storeName,
            String conditional,
            LocalDate deadline,
            int pointReward
    ) { }
}
