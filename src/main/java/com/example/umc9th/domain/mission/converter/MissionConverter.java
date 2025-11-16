package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;

public class MissionConverter {

    public static Mission toMission(MissionReqDTO.CreateMissionDTO dto, Store store) {
        return Mission.builder()
                .deadline(dto.deadline())
                .conditional(dto.conditional())
                .point(dto.point())
                .store(store)
                .build();
    }

    public static MissionResDTO.CreateMissionDTO toCreateMissionDTO(Mission mission) {
        return MissionResDTO.CreateMissionDTO.builder()
                .missionId(mission.getId())
                .deadline(mission.getDeadline())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .storeId(mission.getStore().getId())
                .build();
    }
}