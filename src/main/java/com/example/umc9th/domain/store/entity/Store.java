package com.example.umc9th.domain.store.entity;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manager_number", nullable = false)
    private Long managerNumber;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();

}
