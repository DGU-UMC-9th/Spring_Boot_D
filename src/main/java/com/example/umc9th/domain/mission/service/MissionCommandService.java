package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO;

public interface MissionCommandService {

    ChallengeMissionResponseDTO challengeMission(Long memberId, Long missionId);
}
