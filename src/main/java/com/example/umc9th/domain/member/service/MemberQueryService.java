package com.example.umc9th.domain.member.service;

import com.example.umc9th.global.auth.dto.MemberReqDTO;
import com.example.umc9th.global.auth.dto.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {


    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);

}
