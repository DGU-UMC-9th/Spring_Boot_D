package com.example.umc9th.domain.mission.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class HomeMissionResponseDto {
    private String locationName;
    private String storeName;
    private String conditional;
    private int point;
    private String deadline;
}