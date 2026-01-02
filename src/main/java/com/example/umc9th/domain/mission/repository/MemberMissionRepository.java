package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO;
import com.example.umc9th.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


    @Query("SELECT new com.example.umc9th.domain.mission.dto.MissionResponseDTO(" +
            "ms.id, s.name, ms.conditional, ms.pointReward, CAST(m.status AS string)) " +
            "FROM MemberMission m " +
            "JOIN m.mission ms " +
            "JOIN ms.store s " +
            "WHERE m.member.id = :memberId AND m.status = :status " +
            "ORDER BY m.createdAt DESC")
    Page<MissionResponseDTO> findMemberMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            Pageable pageable
    );

    @Query("SELECT COUNT(m) " +
            "FROM MemberMission m JOIN m.mission ms JOIN ms.store s " +
            "WHERE m.member.id = :memberId " +
            "AND m.status = 'COMPLETED' " +
            "AND s.region.id = :regionId")
    Long countCompletedMissionsByMemberAndRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId
    );


}