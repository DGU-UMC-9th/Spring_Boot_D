package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Reply;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class ReviewSearchResponseDto {
    private Long id;
    private String content;
    private float star;
    private Reply reply;
}
