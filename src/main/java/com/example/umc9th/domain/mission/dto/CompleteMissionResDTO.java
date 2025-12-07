package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MemberMissionStatus;
import lombok.Builder;

import java.util.List;

public class CompleteMissionResDTO {

    @Builder
    public record MissionPreviewListDTO(
            List<CompleteMissionResDTO.MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MissionPreviewDTO(
        Long memberId,
        Long missionId,
        String storeName,
        String conditional,
        int pointReward,
        String status
    ) { }
}
