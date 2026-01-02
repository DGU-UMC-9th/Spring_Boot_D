package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private StoreRepository storeRepository;


    @Test
    @DisplayName("리뷰 작성 성공 테스트")
    void createReview_success() {
        // Given
        Long storeId = 1L;
        Long memberId = 2L;
        ReviewRequestDTO request = new ReviewRequestDTO("새로운 리뷰 내용", 5.0);

        // Mock 엔티티 생성
        Member mockMember = Member.builder().id(memberId).build();
        Store mockStore = Store.builder().id(storeId).build();
        Review mockReview = Review.builder().id(10L).member(mockMember).store(mockStore).content(request.getContent()).rating(request.getRating()).build();

        // Mocking 설정
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(mockMember)); // 회원 조회 성공
        when(storeRepository.findById(storeId)).thenReturn(Optional.of(mockStore));   // 가게 조회 성공
        when(reviewRepository.save(any(Review.class))).thenReturn(mockReview);        // save 호출 시 mockReview 반환

        // When
        Review createdReview = reviewService.createReview(storeId, memberId, request);

        // Then
        // 1. save 메서드가 호출되었는지 검증
        verify(reviewRepository).save(any(Review.class));
        // 2. 반환된 리뷰 내용 검증
        assertThat(createdReview.getContent()).isEqualTo("새로운 리뷰 내용");
        assertThat(createdReview.getRating()).isEqualTo(5.0);
    }

    @Test
    @DisplayName("작성한 리뷰 목록 조회 테스트")
    void getMyReviewsTest() {
        // Given
        Long memberId = 1L;

        // JPQL 쿼리 결과로 가정할 Mock DTO 리스트
        List<ReviewResponseDTO> mockReviews = List.of(
                ReviewResponseDTO.builder().storeName("신승호").content("맛있어요!").rating(4.5).build(),
                ReviewResponseDTO.builder().storeName("버거킹").content("별로네요.").rating(2.0).build()
        );

        // Mocking 설정: reviewRepository.findMemberReviews(memberId) 호출 시 mockReviews 반환
        when(reviewRepository.findMemberReviews(memberId)).thenReturn(mockReviews);

        // When
        List<ReviewResponseDTO> result = reviewService.getMyReviews(memberId);

        // Then
        // 1. 결과 리스트 크기 검증
        assertThat(result).hasSize(2);
        // 2. 리뷰 내용이 예상대로 포함되어 있는지 검증 (순서 무관)
        assertThat(result.stream().map(ReviewResponseDTO::getContent))
                .containsExactlyInAnyOrder("맛있어요!", "별로네요.");
    }


    @Test
    @DisplayName("리뷰 작성 실패 (회원 없음) 테스트")
    void createReview_failure_member_not_found() {
        // Given
        Long nonExistentMemberId = 999L;
        Long storeId = 1L; // 가게 ID는 사용되지 않지만, 메서드 호출을 위해 필요
        ReviewRequestDTO request = new ReviewRequestDTO("내용", 4.0);

        // Mocking 설정: 회원 조회 실패 (가장 먼저 발생하는 실패 조건을 설정)
        // storeRepository.findById에 대한 Mocking은 제거합니다.
        when(memberRepository.findById(nonExistentMemberId)).thenReturn(Optional.empty());

        // When & Then
        // 회원 조회 실패 시 바로 예외가 발생하는지 검증
        assertThrows(NoSuchElementException.class,
                () -> reviewService.createReview(storeId, nonExistentMemberId, request));
    }

}