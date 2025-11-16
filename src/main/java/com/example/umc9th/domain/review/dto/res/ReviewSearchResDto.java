package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.review.entity.Reply;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class ReviewSearchResDto {
    private Long id;
    private String content;
    private float star;
    private Reply reply;
}
