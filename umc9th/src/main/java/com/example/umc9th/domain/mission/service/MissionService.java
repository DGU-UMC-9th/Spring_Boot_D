package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.HomeMissionResDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.dto.res.MissionStatusResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    private static final int PAGE_SIZE = 10;

    public List<MissionStatusResDTO> getMissionsByStatus(Long memberId, Boolean isComplete, int page, int size) {
        Page<MemberMission> missionPage = memberMissionRepository.findMissionByStatus(
                memberId,
                isComplete,
                PageRequest.of(page, size)
        );

        return missionPage.stream()
                .map(mm -> new MissionStatusResDTO(
                        mm.getMission().getId(),
                        mm.getMission().getStore().getName(),
                        mm.getMission().getConditional(),
                        mm.getMission().getPoint(),
                        mm.getMission().getDeadline().toString(),
                        mm.getIsComplete()
                ))
                .collect(Collectors.toList());
    }

    public List<HomeMissionResDTO> getHomeMissions(Long memberId, int page, int size) {
        if (!memberMissionRepository.existsById(memberId)) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND);
        }
        Page<MemberMission> missionPage = memberMissionRepository.findHomeMissions(
                memberId,
                PageRequest.of(page, size)
        );

        return missionPage.stream()
                .map(mm -> new HomeMissionResDTO(
                        mm.getMission().getStore().getLocation().getName(),
                        mm.getMission().getStore().getName(),
                        mm.getMission().getConditional(),
                        mm.getMission().getPoint(),
                        mm.getMission().getDeadline().toString()
                ))
                .collect(Collectors.toList());
    }

    public List<MissionResDTO.StoreMissionDTO> getMissionsByStore(Long storeId, int page) {

        storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));
        int pageIndex = page - 1;
        Page<Mission> missionPage = missionRepository.findByStoreId(
                storeId,
                PageRequest.of(pageIndex, PAGE_SIZE)
        );

        return missionPage.stream()
                .map(MissionConverter::toStoreMissionDTO)
                .toList();
    }
}