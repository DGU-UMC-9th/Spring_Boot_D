package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.MypageResponseDto;
import com.example.umc9th.domain.member.dto.MypageResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MypageResponseDto getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        String phoneStatus = (member.getPhoneNumber() == null || member.getPhoneNumber().trim().isEmpty())
                ? "미인증"
                : "인증";

        return new MypageResponseDto(
                member.getName(),
                member.getEmail(),
                phoneStatus,
                member.getPoint()
        );
    }
}