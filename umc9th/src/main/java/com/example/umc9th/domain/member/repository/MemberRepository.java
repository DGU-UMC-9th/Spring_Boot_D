package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
