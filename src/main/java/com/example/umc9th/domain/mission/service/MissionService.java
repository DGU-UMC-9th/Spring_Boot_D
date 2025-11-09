package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.HomeMissionResponseDTO;
import com.example.umc9th.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;

import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public Page<MissionResponseDTO> getOngoingMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findMemberMissionsByStatus(
                memberId,
                MemberMissionStatus.ONGOING.name(), // 진행 중 상태
                pageable
        );
    }

    public Page<MissionResponseDTO> getCompletedMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findMemberMissionsByStatus(
                memberId,
                MemberMissionStatus.COMPLETED.name(), // 완료 상태
                pageable
        );
    }

    public HomeMissionResponseDTO getHomeMissions(Long memberId, Long regionId, Pageable pageable) {

        //  지역에서 도전 가능한 미션 목록 조회
        Page<ChallengeMissionResponseDTO> challengeableMissions =
                missionRepository.findChallengeableMissionsByRegion(regionId, LocalDate.now(), pageable);

        //  지역에서 회원이 성공한 미션 개수 조회
        Long completedCount = memberMissionRepository.countCompletedMissionsByMemberAndRegion(memberId, regionId);

        //  총 미션 개수는 해당 지역의 도전 가능한 미션 총 개수
        Long totalCount = challengeableMissions.getTotalElements();

        // 두 결과를 최종 응답 DTO로 묶어서 반환
        return HomeMissionResponseDTO.builder()
                .completedCount(completedCount)
                .totalCount(totalCount)
                .missionList(challengeableMissions)
                .build();
    }

}