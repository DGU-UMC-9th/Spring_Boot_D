package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionResponseDTO {

    private Long missionId;
    private String storeName;
    private String condition;
    private Integer point;
    private String missionStatus;
}