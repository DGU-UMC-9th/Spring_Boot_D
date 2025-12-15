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

    @Builder
    public record ChallengeListDTO(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            String storeName,
            String conditional,
            int point,
            String deadline,
            Boolean isComplete
    ){}
}