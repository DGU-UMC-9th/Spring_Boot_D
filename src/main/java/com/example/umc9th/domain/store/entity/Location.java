package com.example.umc9th.domain.store.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.store.entity.Store;
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
@Table(name = "location")
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // 지역에 속한 가게들
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();
}
