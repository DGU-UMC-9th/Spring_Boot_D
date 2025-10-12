package com.example.umc9th.domain.term.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.member.entity.MemberTerm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum", nullable = false)
    private TermType type;

    @Column(name = "comment", length = 500)
    private String comment;

    // 약관과 회원의 다대다 관계
    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<MemberTerm> memberTerms = new ArrayList<>();
}
