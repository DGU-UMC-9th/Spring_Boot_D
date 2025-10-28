package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 필요 시 중복/조건 체크용 메서드 예시
    boolean existsByMember_IdAndStore_IdAndContent(Long memberId, Long storeId, String content);
}