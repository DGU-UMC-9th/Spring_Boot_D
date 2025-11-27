package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;

import java.util.List;

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

    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .build();
    }

    public static List<MissionResDTO.StoreMissionDTO> toStoreMissionDTOList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toStoreMissionDTO)
                .toList();
    }
}