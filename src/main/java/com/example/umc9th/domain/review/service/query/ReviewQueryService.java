package com.example.umc9th.domain.review.service.query;


import com.example.umc9th.domain.review.dto.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreviewListDTO findReview(String storeName, Integer page);
}
