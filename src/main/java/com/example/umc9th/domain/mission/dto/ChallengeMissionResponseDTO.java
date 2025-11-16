package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeMissionResponseDTO {

    private Long missionId;
    private String storeName;
    private LocalDateTime deadline;
    private String condition;
    private Integer point;
}