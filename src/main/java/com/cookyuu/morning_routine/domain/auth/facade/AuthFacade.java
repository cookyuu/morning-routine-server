package com.cookyuu.morning_routine.domain.auth.facade;

import com.cookyuu.morning_routine.domain.auth.dto.LoginDto;
import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthFacade {

    private final MemberService memberService;

    public LoginDto.Response login(LoginDto.Request loginInfo) {
        // 회원정보 검증

        // Access Token 생성
        return LoginDto.Response.builder()
                .accessToken("")
                .build();
    }

    public void signup(SignupDto.Request signupInfo) {

    }
}
