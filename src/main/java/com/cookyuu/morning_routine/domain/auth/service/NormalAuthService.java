package com.cookyuu.morning_routine.domain.auth.service;

import com.cookyuu.morning_routine.domain.auth.dto.LoginDto;
import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.member.service.MemberService;
import com.cookyuu.morning_routine.global.utils.AuthUtils;
import com.cookyuu.morning_routine.global.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NormalAuthService implements AuthService{

    private final ValidateUtils validateUtils;
    private final AuthUtils authUtils;
    private final MemberService memberService;

    @Override
    public LoginDto.Response login(Object loginInfo) {
        return null;
    }

    @Override
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
