package com.example.umc9th.domain.mission.service;


import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;

public interface MissionQueryService {
    ReviewResDTO.ReviewPreviewListDTO findReview(String storeName, Integer page);

    MissionResDTO.MissionPreviewListDTO findMissions(String storeName, Integer page);
}
