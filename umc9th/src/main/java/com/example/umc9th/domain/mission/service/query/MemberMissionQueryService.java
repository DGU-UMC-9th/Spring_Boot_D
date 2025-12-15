package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;

import java.util.List;

public interface MemberMissionQueryService {

    List<MemberMissionResDTO.ChallengeListDTO> getOngoingMissions(Long memberId, int page);
    List<MemberMissionResDTO.ChallengeListDTO> getCompletedMissions(Long memberId, int page);
}