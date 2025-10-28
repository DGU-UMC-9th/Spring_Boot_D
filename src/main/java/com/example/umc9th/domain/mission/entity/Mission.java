package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.mapping.entity.MemberMission;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    /** 마감일 */
    @Column(nullable = false)
    private LocalDate deadline;

    /** 조건(문구). SQL 예약어 피하려고 column name은 'conditional'로 지정 */
    @Column(name = "conditional", nullable = false, length = 100)
    private String conditional;

    /** 성공 포인트 */
    @Column(nullable = false)
    private Integer point;

    /** 가게(FK) : 미션 N : 1 스토어 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    /** 회원-미션 조인(양방향 필요할 때만 사용) */
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();

    /* 연관관계 편의 메서드 (선택) */
    public void addMemberMission(MemberMission mm) {
        memberMissions.add(mm);
        mm.setMission(this);
    }
}