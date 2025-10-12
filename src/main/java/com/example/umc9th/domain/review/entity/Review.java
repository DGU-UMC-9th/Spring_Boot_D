package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "star", nullable = false)
    private Float star;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    // 리뷰와 회원의 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Member member;

    // 리뷰와 가게의 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    // 리뷰의 답글들
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    // 리뷰의 사진들
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();
}
