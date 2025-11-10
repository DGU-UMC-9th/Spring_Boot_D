package com.example.umc9th.domain.review.dto;

public record ReviewFilterRequest(Long storeId,
                                  Integer starRating,
                                  int page,
                                  int size) {

    public ReviewFilterRequest(Long storeId, Integer starRating, Integer page, Integer size) {
        this(storeId,
                starRating,
                (page == null || page < 0) ? 0 : page,
                (size == null || size < 1) ? 10 : size);
    }

    public ReviewFilterRequest {
        page = Math.max(page, 0);
        size = Math.max(size, 1);
    }
}

