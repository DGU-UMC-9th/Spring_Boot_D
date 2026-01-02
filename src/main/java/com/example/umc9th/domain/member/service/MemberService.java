package com.example.umc9th.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.umc9th.domain.member.dto.MemberInfoResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberInfoResponseDTO getMemberInfo(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException( memberId + "를 찾을 수 없습니다"));

        return MemberInfoResponseDTO.builder()
                .nickname(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .email(member.getEmail())
                .build();
    }
}