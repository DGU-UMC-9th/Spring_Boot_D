package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {
    MissionResDTO.CreateMissionDTO createMission(Long storeId, MissionReqDTO.CreateMissionDTO dto);
}