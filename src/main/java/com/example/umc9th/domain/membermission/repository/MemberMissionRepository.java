package com.example.umc9th.domain.membermission.repository;

import com.example.umc9th.domain.membermission.entity.MemberMission;
import com.example.umc9th.domain.membermission.enums.MissionStatus;
import com.example.umc9th.domain.mission.dto.MyMissionItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
    select new com.example.umc9th.domain.mission.dto.MyMissionItemDto(
        mm.id,
        m.id,
        s.name,
        m.conditional,
        m.point,
        mm.status,
        mm.updatedAt
    )
    from MemberMission mm
    join mm.mission m
    join m.store s
    where mm.member.id = :memberId
      and mm.status = :status
    order by mm.updatedAt desc
""")
    Page<MyMissionItemDto> findMyMissionItems(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable);
}