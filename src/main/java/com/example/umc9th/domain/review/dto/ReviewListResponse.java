package com.example.umc9th.domain.review.dto;

import java.util.List;

public record ReviewListResponse(List<ReviewInfo> reviews,
                                 int totalCount,
                                 boolean hasNext) {
}

