package com.cookyuu.morning_routine.domain.auth.facade;

import com.cookyuu.morning_routine.domain.auth.dto.JWTUserInfo;
import com.cookyuu.morning_routine.domain.auth.dto.LoginDto;
import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.member.service.MemberService;
import com.cookyuu.morning_routine.global.code.CookieCode;
import com.cookyuu.morning_routine.global.code.RedisKeyCode;
import com.cookyuu.morning_routine.global.utils.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthFacade {

    @Value("${auth.jwt.refresh.exp}")
    private String refreshTokenExp;
    @Value("${auth.jwt.access.exp}")
    private String accessTokenExp;

    private final ValidateUtils validateUtils;
    private final AuthUtils authUtils;
    private final JwtUtils jwtUtils;
    private final CookieUtils cookieUtils;
    private final RedisUtils redisUtils;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public LoginDto.Response login(Object loginInfo, HttpServletResponse response) {
        if (!(loginInfo instanceof LoginDto.Request req)) {
            log.error("[Login::Error] Fail Down casting Object to LoginDto.Request");
            throw new IllegalArgumentException("로그인 실패");
        }
        JWTUserInfo userInfo = memberService.checkLoginCredentials(req.getLoginId(), req.getPassword());
        String accessToken = jwtUtils.createAccessToken(userInfo);
        String refreshToken = jwtUtils.createRefreshToken(userInfo);
        Cookie cookie = cookieUtils.setCookieExpire(CookieCode.REFRESH_TOKEN, refreshToken, Integer.parseInt(refreshTokenExp));
        response.addCookie(cookie);

        redisUtils.setDataExpire(RedisKeyCode.REFRESH_TOKEN.getSeparator()+req.getLoginId(), refreshToken, Integer.parseInt(refreshTokenExp));
        return LoginDto.Response.builder()
                .accessToken(accessToken)
                .build();
    }

    @Transactional
    public void signup(Object signupInfo) {
        if (!(signupInfo instanceof SignupDto.Request req)) {
            log.error("[Signup::Error] Fail Down casting Object to SignupDto.Request");
            throw new IllegalArgumentException("회원가입 실패");
        }
        String email = req.getEmail();
        String loginId = req.getLoginId();
        String phoneNumber = req.getPhoneNumber();
        String password = req.getPassword();

        validateSignupInfo(email, loginId, phoneNumber, password);
        req.setEncPassword(authUtils.encryptPassword(password));
        memberService.isDuplicateLoginInfo(email, loginId, phoneNumber);
        memberService.saveMember(req.of());
    }

    private void validateSignupInfo(String email, String loginId, String phoneNumber, String password) {
        validateUtils.isAvailableEmailFormat(email);
        validateUtils.isAvailableLoginIdFormat(loginId);
        validateUtils.isAvailablePhoneNumberFormat(phoneNumber);
        validateUtils.isAvailablePasswordFormat(password);
    }
}
