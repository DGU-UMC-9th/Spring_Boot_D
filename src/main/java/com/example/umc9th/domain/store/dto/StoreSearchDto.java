package com.example.umc9th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreSearchDto {
    private Long id;
    private String name;
    private String region;
    private String category;
    private String address;
}