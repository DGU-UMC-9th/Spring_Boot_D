package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.membermission.enums.MissionStatus;
import com.example.umc9th.domain.membermission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.dto.MyMissionItemDto;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyMissionService {
    private final MemberMissionRepository memberMissionRepository;

    public Page<MyMissionItemDto> getMyMissions(Long memberId, MissionStatus status, Pageable pageable) {
        return memberMissionRepository.findMyMissionItems(memberId, status, pageable);
    }
}