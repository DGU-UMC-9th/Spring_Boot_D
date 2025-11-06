package com.example.umc9th.domain.food.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodCategory {
    KOREAN_JAPANESE_CHINESE("한식,일식,중식"),
    WESTERN("양식"),
    ASIAN("아시안"),
    DESSERT("디저트"),
    FAST_FOOD("패스트푸드");

    private final String description;
}
