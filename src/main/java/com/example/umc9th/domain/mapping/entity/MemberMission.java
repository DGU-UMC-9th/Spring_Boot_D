package com.example.umc9th.domain.mapping.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        name = "member_mission",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_member_mission_memberid_missionid",
                        columnNames = {"member_id", "mission_id"}
                )
        }
)
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    /** 완료 여부 (bit/boolean). 기본값 false */
    @Column(name = "is_complete", nullable = false)
    private Boolean complete;

    /** 회원(FK) : 다대일 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /** 미션(FK) : 다대일 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    /* 편의 세터 (양방향 세팅 시 사용) */
    public void setMember(Member member) {
        this.member = member;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    /* 기본값 부여를 위해 Builder에 디폴트 값 설정도 가능 */
    @PrePersist
    void prePersist() {
        if (complete == null) complete = false;
    }
}