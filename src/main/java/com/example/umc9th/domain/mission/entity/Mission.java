package com.example.umc9th.domain.mission.entity;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import com.sun.jdi.event.StepEvent;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "mission")
@EntityListeners(AuditingEntityListener.class)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "conditional", nullable = false)
    private String conditional;

    @Column(name = "point", nullable = false)
    private int point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionList = new ArrayList<>();

}
