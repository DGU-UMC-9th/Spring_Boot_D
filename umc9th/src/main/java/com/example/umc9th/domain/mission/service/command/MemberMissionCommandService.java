package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionCommandService {
    MemberMissionResDTO.ChallengeMissionDTO challengeMission(Long memberId, Long missionId);
    MemberMissionResDTO.ChallengeMissionDTO completeMission(Long memberId, Long missionId);
}