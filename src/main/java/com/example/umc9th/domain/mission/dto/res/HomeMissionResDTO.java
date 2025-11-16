package com.example.umc9th.domain.mission.dto.res;

import lombok.*;

@Getter
@AllArgsConstructor
public class HomeMissionResDTO {
    private String locationName;
    private String storeName;
    private String conditional;
    private int point;
    private String deadline;
}