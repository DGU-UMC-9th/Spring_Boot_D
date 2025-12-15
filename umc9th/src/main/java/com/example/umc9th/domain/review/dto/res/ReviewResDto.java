package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.review.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.example.umc9th.domain.review.entity.QReply.reply;

@Getter
@AllArgsConstructor
public class ReviewResDto {

    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            String content,
            float star,
            Long memberId,
            Long storeId
    ) {}

    @Builder
    public record ReviewSearchResDto(
            Long id,
            String content,
            float star,
            Reply reply
    ){}
}