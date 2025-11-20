package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.HomeMissionResponseDto;
import com.example.umc9th.domain.mission.dto.MissionStatusResponseDto;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public List<MissionStatusResponseDto> getMissionsByStatus(Long memberId, Boolean isComplete, int page, int size) {
        Page<MemberMission> missionPage = memberMissionRepository.findMissionByStatus(
                memberId,
                isComplete,
                PageRequest.of(page, size)
        );

        return missionPage.stream()
                .map(mm -> new MissionStatusResponseDto(
                        mm.getMission().getId(),
                        mm.getMission().getStore().getName(),
                        mm.getMission().getConditional(),
                        mm.getMission().getPoint(),
                        mm.getMission().getDeadline().toString(),
                        mm.getIsComplete()
                ))
                .collect(Collectors.toList());
    }

    public List<HomeMissionResponseDto> getHomeMissions(Long memberId, int page, int size) {
        if (!memberMissionRepository.existsById(memberId)) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND);
        }
        Page<MemberMission> missionPage = memberMissionRepository.findHomeMissions(
                memberId,
                PageRequest.of(page, size)
        );

        return missionPage.stream()
                .map(mm -> new HomeMissionResponseDto(
                        mm.getMission().getStore().getLocation().getName(),
                        mm.getMission().getStore().getName(),
                        mm.getMission().getConditional(),
                        mm.getMission().getPoint(),
                        mm.getMission().getDeadline().toString()
                ))
                .collect(Collectors.toList());
    }
}