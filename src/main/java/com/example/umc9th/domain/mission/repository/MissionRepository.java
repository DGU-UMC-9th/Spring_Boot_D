package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {


    @Query("SELECT new com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO(" +
            "ms.id, s.name, ms.deadline, ms.conditional, ms.pointReward) " +
            "FROM Mission ms JOIN ms.store s " +
            "WHERE s.region.id = :regionId " +
            "AND ms.deadline > :now " +
            "ORDER BY ms.createdAt DESC")
    Page<ChallengeMissionResponseDTO> findChallengeableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("now") LocalDate now,
            Pageable pageable
    );

    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);



}