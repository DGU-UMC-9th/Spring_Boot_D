package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();
    }

    public static MemberMissionResDTO.ChallengeMissionDTO toChallengeDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .isComplete(memberMission.getIsComplete())
                .build();
    }

    public static MemberMissionResDTO.ChallengeListDTO toChallengeListDTO(MemberMission mm) {
        return MemberMissionResDTO.ChallengeListDTO.builder()
                .memberMissionId(mm.getId())
                .memberId(mm.getMember().getId())
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .conditional(mm.getMission().getConditional())
                .point(mm.getMission().getPoint())
                .deadline(mm.getMission().getDeadline().toString())
                .isComplete(mm.getIsComplete())
                .build();
    }

    public static List<MemberMissionResDTO.ChallengeListDTO> toChallengeListDTOList(List<MemberMission> memberMissions) {
        return memberMissions.stream()
                .map(MemberMissionConverter::toChallengeListDTO)
                .toList();
    }
}
