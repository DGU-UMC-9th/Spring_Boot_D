package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.OngoingMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {


    public static OngoingMissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(
            Page<MemberMission> result) {
        return OngoingMissionResDTO.MissionPreviewListDTO.builder()
                .missionList(result.getContent()
                        .stream().map(MemberMissionConverter::toMissionPreviewDTO).toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static OngoingMissionResDTO.MissionPreviewDTO toMissionPreviewDTO(
            MemberMission memberMission
    ) {
        return OngoingMissionResDTO.MissionPreviewDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .conditional(memberMission.getMission().getConditional())
                .deadline(memberMission.getMission().getDeadline())
                .pointReward(memberMission.getMission().getPointReward())
                .build();
    }

}
