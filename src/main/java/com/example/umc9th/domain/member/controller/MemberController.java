package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MypageResponseDto;
import com.example.umc9th.domain.member.dto.MypageResponseDto;
import com.example.umc9th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}/mypage")
    public ResponseEntity<MypageResponseDto> getMyPage(@PathVariable Long id) {
        MypageResponseDto response = memberService.getMyPage(id);
        return ResponseEntity.ok(response);
    }
}