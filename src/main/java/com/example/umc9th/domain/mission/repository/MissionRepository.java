package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MissionRepository extends JpaRepository<Mission, Long> {


    @Query("SELECT new com.example.umc9th.domain.mission.dto.ChallengeMissionResponseDTO(" +
            "ms.id, s.name, ms.deadline, ms.condition, ms.point) " +
            "FROM Mission ms JOIN ms.store s " +
            "WHERE s.region.id = :regionId " +
            "AND ms.deadline > :now " +
            "ORDER BY ms.createdAt DESC")
    Page<ChallengeMissionResponseDTO> findChallengeableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );
}