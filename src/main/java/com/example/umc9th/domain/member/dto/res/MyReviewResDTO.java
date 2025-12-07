package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MyReviewResDTO {

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
            String storeName,
            Double score,
            String body,
            LocalDate createdAt
    ){}
}
