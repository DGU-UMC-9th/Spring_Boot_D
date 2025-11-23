package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeMissionResponseDTO {
    private Long missionId;
    private String storeName;
    private LocalDate deadline;
    private String condition;
    private Integer point;
}