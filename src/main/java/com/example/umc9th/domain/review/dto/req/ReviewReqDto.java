package com.example.umc9th.domain.review.dto.req;
import lombok.*;

@Getter
public class ReviewReqDto {
    private String content;
    private float star;
    private Long memberId;
    private Long storeId;
}