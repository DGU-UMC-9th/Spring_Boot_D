package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.HomeMissionItemDto;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeMissionService {

    private final MissionRepository missionRepository;

    public Page<HomeMissionItemDto> getHomeMissions(Long memberId, Long locationId, Pageable pageable) {
        return missionRepository.findHomeMissions(memberId, locationId, pageable);
    }
}