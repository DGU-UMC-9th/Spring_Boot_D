package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HomeMissionItemDto {
    private Long missionId;
    private String storeName;
    private String conditionalText; // "10,000원 이상 식사 시" 등
    private Integer point;
    private String locationName;
    private LocalDate deadline;     // D-Day 계산은 프론트/앱에서
}