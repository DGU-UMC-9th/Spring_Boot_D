package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.member.entity.MemberMission;
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
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "conditional", nullable = false, length = 200)
    private String conditional;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    // 미션과 가게의 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    // 미션 도전 기록
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();
}
