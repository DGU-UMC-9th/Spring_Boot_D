package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    public List<MemberMissionResDTO.ChallengeListDTO> getOngoingMissions(Long memberId, int page) {

        int pageIndex = page - 1;

        Page<MemberMission> missionPage = memberMissionRepository.findMissionByStatus(
                memberId,
                false,
                PageRequest.of(pageIndex, PAGE_SIZE, Sort.by("id").descending())
        );
        return MemberMissionConverter.toChallengeListDTOList(missionPage.getContent());
    }

    @Override
    public  List<MemberMissionResDTO.ChallengeListDTO> getCompletedMissions(Long memberId, int page) {
        int pageIndex = page - 1;
        Page<MemberMission> missionPage = memberMissionRepository.findMissionByStatus(
                memberId,
                true,
                PageRequest.of(pageIndex, PAGE_SIZE, Sort.by("id").descending())
        );

        return MemberMissionConverter.toChallengeListDTOList(missionPage.getContent());
    }
}