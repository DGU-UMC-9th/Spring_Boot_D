package com.example.umc9th.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("SELECT new com.example.umc9th.domain.review.dto.ReviewResponseDTO(" +
            "r.id, s.name, r.content, r.rating) " +
            "FROM Review r JOIN r.store s " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.createdAt DESC")
    List<ReviewResponseDTO> findMemberReviews(@Param("memberId") Long memberId);


}