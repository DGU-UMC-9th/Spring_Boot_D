package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.MemberInfoResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks // 테스트 대상 서비스
    private MemberService memberService;

    @Mock // Mocking 할 Repository
    private MemberRepository memberRepository;

    @Test
    @DisplayName("마이페이지 정보 조회 성공 테스트")
    void getMemberInfo_success() {
        // Given
        Long memberId = 1L;
        Member mockMember = Member.builder()
                .id(memberId)
                .name("nickname012") // 닉네임 (name)
                .email("test@naver.com")
                .phoneNumber("010-1234-5678")
                .point(2500)
                .build();

        // Mocking 설정: findById 호출 시 mockMember 반환
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(mockMember));

        // When
        MemberInfoResponseDTO result = memberService.getMemberInfo(memberId);

        // Then
        assertThat(result.getNickname()).isEqualTo("nickname012");
        assertThat(result.getEmail()).isEqualTo("test@naver.com");
    }

    @Test
    @DisplayName("마이페이지 정보 조회 실패 (회원 없음) 테스트")
    void getMemberInfo_failure_not_found() {
        // Given
        Long nonExistentId = 999L;

        // Mocking 설정: findById 호출 시 빈 Optional 반환
        when(memberRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // When & Then
        // NoSuchElementException이 발생하는지 확인
        assertThrows(NoSuchElementException.class, () -> memberService.getMemberInfo(nonExistentId));
    }
}