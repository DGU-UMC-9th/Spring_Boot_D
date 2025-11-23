package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.dto.res.MyReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;

public interface MemberQueryService {


    MyReviewResDTO.ReviewPreviewListDTO findReview(Long memberId, Integer page);
}
