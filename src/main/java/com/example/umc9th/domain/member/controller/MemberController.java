package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MypageResponseDto;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}/mypage")
    public ResponseEntity<ApiResponse<MypageResponseDto>> getMyPage(@PathVariable Long id) {
        MypageResponseDto response = memberService.getMyPage(id);
        var sc = MemberSuccessCode.MYPAGE_FETCHED;
        return ResponseEntity.status(sc.getStatus())
                .body(ApiResponse.onSuccess(sc, response));
    }
}