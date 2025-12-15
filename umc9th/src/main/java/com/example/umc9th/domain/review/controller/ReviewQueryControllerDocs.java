package com.example.umc9th.domain.review.controller;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ReviewQueryControllerDocs {


    @Operation(
            summary = "내가 쓴 리뷰 목록 조회 API",
            description = """
                    내가 작성한 리뷰를 페이지네이션으로 조회합니다."""
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 리뷰 목록을 조회했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "요청 값이 올바르지 않습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원 또는 데이터가 존재하지 않습니다.")
    })
    ResponseEntity<ApiResponse<Page<ReviewResDto.ReviewSearchResDto>>> getMyReviews(
            @Parameter(description = "회원 ID")
            Long memberId,
            @Parameter(description = "가게 이름(부분 검색)")
            String storeName,
            @Parameter(description = "별점 필터 (예: 4.0이면 4.0 이상 5.0 미만)")
            Float star,
            @Parameter(description = "1 이상의 페이지 번호")
            @ValidPage Integer page
            );
}
