package com.example.umc9th.domain.review.dto.req;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
public class ReviewReqDto {

    public record CreateReviewDTO(
            @NotBlank(message = "리뷰 내용을 입력해주세요.")
            String content,

            @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5점 이하이어야 합니다.")
            float star
    ) {}
}