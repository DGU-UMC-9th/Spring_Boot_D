package com.example.umc9th.domain.food.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.member.entity.MemberFood;
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
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum", nullable = false)
    private FoodCategory category;

    // 음식과 회원의 다대다 관계 (선호 음식)
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoods = new ArrayList<>();
}
