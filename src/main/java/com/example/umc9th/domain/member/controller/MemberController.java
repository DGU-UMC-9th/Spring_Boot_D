package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberInfoResponseDTO;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.MemberCommandService;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
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
    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signUp(dto));
    }


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