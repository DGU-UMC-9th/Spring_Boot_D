package com.example.umc9th.domain.mission.service;


import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.OngoingMissionResDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;

public interface MissionQueryService {
    ReviewResDTO.ReviewPreviewListDTO findReview(String storeName, Integer page);

    MissionResDTO.MissionPreviewListDTO findStoreMissions(String storeName, Integer page);
    OngoingMissionResDTO.MissionPreviewListDTO findMemberMissions(Long memberId, Integer page);
}
