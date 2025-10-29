package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.HomeMissionItemDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 홈 화면: 현재 지역에서 "도전 가능한" 미션 목록
     * - 지역(locationId) 일치
     * - 마감일이 오늘 이후
     * - 해당 회원(memberId)이 아직 이 미션에 참여 기록이 없음(진행/완료 모두 미참여)
     *   -> 필요에 따라 조건 조정 가능
     */
    @Query("""
        select new com.example.umc9th.domain.mission.dto.HomeMissionItemDto(
            m.id,
            s.name,
            m.conditional,
            m.point,
            l.name,
            m.deadline
        )
        from Mission m
            join m.store s
            join s.location l
        where l.id = :locationId
          and m.deadline >= current_date
          and not exists (
              select 1
              from MemberMission mm
              where mm.mission = m
                and mm.member.id = :memberId
          )
        order by m.deadline asc
        """)
    Page<HomeMissionItemDto> findHomeMissions(
            @Param("memberId") Long memberId,
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}