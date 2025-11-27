package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDTO.ChallengeMissionDTO challengeMission(Long memberId, Long missionId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .ifPresent(mm -> {
                    throw new MemberMissionException(MemberMissionErrorCode.ALREADY_CHALLENGING);
                });

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);

        MemberMission saved = memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toChallengeDTO(saved);
    }

    @Override
    public MemberMissionResDTO.ChallengeMissionDTO completeMission(Long memberId, Long missionId) {
        Member member  = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        if(Boolean.TRUE.equals(memberMission.getIsComplete())){
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_COMPLETED);
        }

        memberMission.complete();
        MemberMission saved = memberMissionRepository.save(memberMission);
        return MemberMissionConverter.toChallengeDTO(saved);
    }
}