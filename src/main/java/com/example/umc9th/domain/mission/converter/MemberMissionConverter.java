package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.CompleteMissionResDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.OngoingMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MemberMissionStatus;
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

    public static CompleteMissionResDTO.MissionPreviewListDTO toMemberMissionPreviewListDTO(
            Page<MemberMission> result
    ) {
        return CompleteMissionResDTO.MissionPreviewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MemberMissionConverter::toMemberMissionPreviewDTO).toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();

    }

    public static CompleteMissionResDTO.MissionPreviewDTO toMemberMissionPreviewDTO(
    MemberMission memberMission
            ) {
        return CompleteMissionResDTO.MissionPreviewDTO.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .conditional(memberMission.getMission().getConditional())
                .pointReward(memberMission.getMission().getPointReward())
                .status(MemberMissionConverter.statusToString(memberMission.getStatus()))
                .build();
    }

    public static String statusToString(MemberMissionStatus status) {
        if(status == MemberMissionStatus.COMPLETED) return "성공";
        else return "진행중";
    }

}
