package com.example.umc9th.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionStatusResDTO {
    private Long missionId;
    private String storeName;
    private String conditional;
    private int point;
    private String deadline;
    private Boolean isComplete;
}