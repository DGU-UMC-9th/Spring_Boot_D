package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeMissionResponseDTO {

    private Long completedCount;
    private Long totalCount;

    // 미션 목록
    private Page<ChallengeMissionResponseDTO> missionList;
}