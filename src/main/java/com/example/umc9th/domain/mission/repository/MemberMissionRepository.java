package com.example.umc9th.domain.mission.repository;

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
            "WHERE mm.member.id = :memberId AND mm.isComplete = :isComplete " +
            "ORDER BY m.createdAt DESC")
    Page<MemberMission> findMissionByStatus(
            @Param("memberId") Long memberId,
            @Param("isComplete") Boolean isComplete,
            Pageable pageable
    );

    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location l " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.isComplete = false " +
            "ORDER BY m.deadline ASC")
    Page<MemberMission> findHomeMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}