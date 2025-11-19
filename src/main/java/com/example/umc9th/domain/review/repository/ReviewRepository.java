package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {


    @Query("SELECT new com.example.umc9th.domain.review.dto.ReviewResponseDTO(" +
            "r.id, s.name, r.content, r.rating) " +
            "FROM Review r JOIN r.store s " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.createdAt DESC")
    List<ReviewResponseDTO> findMemberReviews(@Param("memberId") Long memberId);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByMember(Member member,  PageRequest pageRequest);
}