package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewPreviewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) { }

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Double score,
            String body,
            LocalDate createdAt
    ){}
}
