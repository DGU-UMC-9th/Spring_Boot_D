package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.CreateMissionDTO createMission(Long storeId, MissionReqDTO.CreateMissionDTO dto) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(dto, store);

        Mission savedMission = missionRepository.save(mission);

        return MissionConverter.toCreateMissionDTO(savedMission);
    }
}