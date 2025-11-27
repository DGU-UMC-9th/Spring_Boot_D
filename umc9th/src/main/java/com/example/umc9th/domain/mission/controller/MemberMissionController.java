package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
@Validated
public class MemberMissionController implements MemberMissionQueryControllerDocs, MemberMissionCommandControllerDocs {

    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberRepository memberRepository;
    private final MemberMissionCommandService memberMissionCommandService;

    @Override
    @GetMapping("/members/{memberId}/ongoing")
    public ResponseEntity<ApiResponse<List<MemberMissionResDTO.ChallengeListDTO>>> getOngoingMissions(
            @PathVariable Long memberId,
            @RequestParam(name = "page") @ValidPage Integer page
    ) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND);
        }

        List<MemberMissionResDTO.ChallengeListDTO> result =
                memberMissionQueryService.getOngoingMissions(memberId, page);

        var sc = MemberMissionSuccessCode.CHALLENGES_FETCHED;

        return ResponseEntity.status(sc.getStatus())
                .body(ApiResponse.onSuccess(sc, result));
    }

    @Override
    @PatchMapping("/members/{memberId}/missions/{missionId}/complete")
    public ResponseEntity<ApiResponse<List<MemberMissionResDTO.ChallengeListDTO>>> completeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId,
            @RequestParam(name = "page") @ValidPage Integer page
    ){
        if (!memberRepository.existsById(memberId)) {
            throw new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND);
        }

        memberMissionCommandService.completeMission(memberId, missionId);
        List<MemberMissionResDTO.ChallengeListDTO> completedMissions =
                memberMissionQueryService.getCompletedMissions(memberId, page);
        var sc = MemberMissionSuccessCode.CHALLENGE_COMPLETED;
        return ResponseEntity.status(sc.getStatus())
                .body(ApiResponse.onSuccess(sc, completedMissions));
    }
}