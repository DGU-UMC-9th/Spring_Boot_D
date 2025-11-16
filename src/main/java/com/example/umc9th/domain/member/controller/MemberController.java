package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.res.MypageResponseDto;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    @GetMapping("/{id}/mypage")
    public ResponseEntity<ApiResponse<MypageResponseDto>> getMyPage(@PathVariable Long id) {
        MypageResponseDto response = memberService.getMyPage(id);
        var sc = MemberSuccessCode.MYPAGE_FETCHED;
        return ResponseEntity.status(sc.getStatus())
                .body(ApiResponse.onSuccess(sc, response));
    }
}