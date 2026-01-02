package com.example.umc9th.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewFilterRequest {

    private Long storeId;

    private Double rating;
}
