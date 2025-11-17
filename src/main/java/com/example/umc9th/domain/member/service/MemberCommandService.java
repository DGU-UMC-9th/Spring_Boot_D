package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {

    public MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto);
}
