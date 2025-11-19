package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.exception.FoodException;
import com.example.umc9th.domain.member.exception.code.FoodErrorCode;
import com.example.umc9th.domain.member.repository.FoodRepository;
import com.example.umc9th.domain.member.repository.MemberFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    //회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    ) {
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

//        if (dto.preferCategory().size() > 1) {
//            List<MemberFood> memberFoodList = new ArrayList<>();
//
//            for (Long id : dto.preferCategory()) {
//                Food food = foodRepository.findById(id)
//                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));
//
//                MemberFood memberFood = MemberFood.builder()
//                        .member(member)
//                        .food(food)
//                        .build();
//
//                memberFoodList.add(memberFood);
//            }
//            memberFoodRepository.saveAll(memberFoodList);
//        }

        if (dto.preferCategory().size() > 1) {
            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(()->new FoodException(FoodErrorCode.NOT_FOUND)))
                                    .build()
                    ).collect(Collectors.toList());
            memberFoodRepository.saveAll(memberFoodList);
        }
        return MemberConverter.toJoinDTO(member);
    }
}
