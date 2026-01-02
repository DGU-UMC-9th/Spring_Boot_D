package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.HomeMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MissionServiceTest {

    @InjectMocks
    private MissionService missionService;

    @Mock
    private MemberMissionRepository memberMissionRepository;
    @Mock
    private MissionRepository missionRepository;

    @Test
    @DisplayName("진행 완료 미션 목록 조회 테스트")
    void getCompletedMissionsTest() {
        // Given
        Long memberId = 1L;
        Pageable pageable = PageRequest.of(0, 5);

        MissionResponseDTO completedMission = MissionResponseDTO.builder()
                .missionStatus(MemberMissionStatus.COMPLETED.name())
                .storeName("완료가게").build();

        Page<MissionResponseDTO> mockPage = new PageImpl<>(List.of(completedMission), pageable, 1);

        // Mocking 설정
        when(memberMissionRepository.findMemberMissionsByStatus(
                eq(memberId),
                eq(MemberMissionStatus.COMPLETED),
                eq(pageable)))
                .thenReturn(mockPage);

        // When
        Page<MissionResponseDTO> result = missionService.getCompletedMissions(memberId, pageable);

        // Then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getStoreName()).isEqualTo("완료가게");
    }

    @Test
    @DisplayName("홈 화면 정보 조회 및 통계 테스트")
    void getHomeMissionsTest() {
        // Given
        Long memberId = 1L;
        Long regionId = 10L;
        Pageable pageable = PageRequest.of(0, 5);

        // Mock 1: 도전 가능 미션 목록
        ChallengeMissionResponseDTO missionDto = ChallengeMissionResponseDTO.builder().missionId(5L).storeName("도전가게").build();
        Page<ChallengeMissionResponseDTO> mockMissionPage = new PageImpl<>(List.of(missionDto), pageable, 10); // 총 10개 가정

        // Mock 2: 완료된 미션 개수
        Long completedCount = 7L; // 7개 완료 가정

        // Mocking 설정
        when(missionRepository.findChallengeableMissionsByRegion(
                eq(regionId), any(LocalDateTime.class), eq(pageable)))
                .thenReturn(mockMissionPage);

        when(memberMissionRepository.countCompletedMissionsByMemberAndRegion(
                eq(memberId), eq(regionId)))
                .thenReturn(completedCount);

        // When
        HomeMissionResponseDTO result = missionService.getHomeMissions(memberId, regionId, pageable);

        // Then
        // 통계 검증: 7/10
        assertThat(result.getCompletedCount()).isEqualTo(7L);
        assertThat(result.getTotalCount()).isEqualTo(10L); // PageImpl의 TotalElements

        // 목록 검증
        assertThat(result.getMissionList().getContent()).hasSize(1);
        assertThat(result.getMissionList().getContent().get(0).getStoreName()).isEqualTo("도전가게");
    }
}