package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionReqDTO {

    public record CreateMissionDTO(
            @NotNull(message = "미션 기간(deadline)을 입력해주세요.")
            LocalDateTime deadline,

            @NotBlank(message = "미션 조건을 입력해주세요.")
            String conditional,

            @NotNull(message = "미션 포인트를 입력해주세요.")
            Integer point
    ) {}
}