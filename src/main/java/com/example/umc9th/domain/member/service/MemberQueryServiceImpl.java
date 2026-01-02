package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.JwtUtil;
import com.example.umc9th.global.auth.dto.MemberReqDTO;
import com.example.umc9th.global.auth.dto.MemberResDTO;
import com.example.umc9th.global.auth.entity.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto)
    {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        if (!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);

        String accessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
