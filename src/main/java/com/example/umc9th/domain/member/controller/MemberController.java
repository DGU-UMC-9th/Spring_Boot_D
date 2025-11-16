package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberInfoResponseDTO;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("{id}/mypage")
    public ApiResponse<MemberInfoResponseDTO> getMyInfo(@PathVariable Long id) {
        MemberInfoResponseDTO response = memberService.getMemberInfo(id);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }


//    @GetMapping("{id}/reviews")
//    public ApiResponse<List<ReviewResponseDTO>> getMyReviews() {
//        List<ReviewResponseDTO> response = memberService.getMyReviews(id);
//
//        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
//    }
}