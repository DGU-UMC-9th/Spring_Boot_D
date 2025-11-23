package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewDetailResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewFilterRequest;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public Page<ReviewDetailResponseDTO> getMyFilteredReviews(
            Long memberId,
            ReviewFilterRequest request,
            Pageable pageable) {

        return reviewRepository.findMyReviewsWithFilter(memberId, request, pageable);
    }

    @Override
    public ReviewResDTO.ReviewPreviewListDTO findReview(String storeName, Integer page) {

        Store store = storeRepository.findByName(storeName).orElseThrow(() ->
                new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public MissionResDTO.MissionPreviewListDTO findMissions(String storeName, Integer page) {
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missions = missionRepository.findAllByStore(store,pageRequest);

        return MissionConverter.toMissionPreviewListDTO(missions);

    }
}