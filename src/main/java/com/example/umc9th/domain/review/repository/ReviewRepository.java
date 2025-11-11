package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.mypage.dto.MyReviewItemDto;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

    // 내가 쓴 리뷰 목록 (JPQL → DTO + 페이징)
    @Query("""
        select new com.example.umc9th.domain.mypage.dto.MyReviewItemDto(
            r.id, s.name, r.star, r.content, r.createdAt
        )
        from Review r
        join r.store s
        where r.member.id = :memberId
        order by r.createdAt desc
    """)
    Page<MyReviewItemDto> findMyReviews(@Param("memberId") Long memberId, Pageable pageable);
}