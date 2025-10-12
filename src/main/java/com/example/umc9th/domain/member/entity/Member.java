package com.example.umc9th.domain.member.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "detail_address", nullable = false, length = 200)
    private String detailAddress;

    @Column(name = "social_uid", nullable = false, length = 100)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(name = "point", nullable = false)
    private Integer point = 0;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 회원과 음식의 다대다 관계 (선호 음식)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoods = new ArrayList<>();

    // 회원과 약관의 다대다 관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    // 회원이 작성한 리뷰들
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    // 회원의 미션 도전 기록
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();
}
