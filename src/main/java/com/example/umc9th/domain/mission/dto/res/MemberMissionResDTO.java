package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberMissionResDTO {

    @Builder
    public record ChallengeMissionDTO(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            Boolean isComplete
    ) {}
}