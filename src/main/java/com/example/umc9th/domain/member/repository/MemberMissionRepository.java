package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId AND mm.status = :status " +
            "ORDER BY m.id DESC")
    Page<MemberMission> findMissionsByMemberAndStatus(
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