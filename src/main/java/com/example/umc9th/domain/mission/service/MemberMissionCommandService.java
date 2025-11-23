package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.CompleteMissionResDTO;

public interface MemberMissionCommandService {
    public CompleteMissionResDTO.MissionPreviewListDTO completeMission(Long memberId, Long missionId, Integer page);
}
