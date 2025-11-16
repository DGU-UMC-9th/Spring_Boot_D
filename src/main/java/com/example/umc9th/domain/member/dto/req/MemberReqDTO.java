package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.enums.Address;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}