package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mypage.dto.MyPageSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
        select new com.example.umc9th.domain.mypage.dto.MyPageSummaryDto(
            m.id,
            m.name,
            m.email,
            (m.phoneNumber is not null),
            coalesce(m.point, 0)
        )
        from Member m
        where m.id = :memberId
    """)
    MyPageSummaryDto findMyPageSummary(@Param("memberId") Long memberId);
}