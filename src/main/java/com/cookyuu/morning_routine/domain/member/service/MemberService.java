package com.cookyuu.morning_routine.domain.member.service;

import com.cookyuu.morning_routine.domain.auth.dto.JWTUserInfo;
import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.member.repository.MemberRepository;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRAuthException;
import com.cookyuu.morning_routine.global.exception.domain.MRMemberException;
import com.cookyuu.morning_routine.global.utils.AuthUtils;
import com.cookyuu.morning_routine.global.utils.ValidateUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthUtils authUtils;
    private final ValidateUtils validateUtils;

    public JWTUserInfo checkLoginCredentials(String loginId, String password) {
        Member member = findByLoginId(loginId);
        log.info("[Login] Check LoginCredential, loginId : {}", member.getLoginId());
        authUtils.checkPassword(member.getPassword(), password);
        JWTUserInfo userInfo = new JWTUserInfo();
        userInfo.of(member);
        return userInfo;
    }

    public void signup(SignupDto.Request req) {
        String email = req.getEmail();
        String loginId = req.getLoginId();
        String phoneNumber = req.getPhoneNumber();
        String password = req.getPassword();

        validateSignupInfo(email, loginId, phoneNumber, password);
        req.setEncPassword(authUtils.encryptPassword(password));
        isDuplicateLoginInfo(email, loginId, phoneNumber);
        saveMember(req.of());
    }

    private void validateSignupInfo(String email, String loginId, String phoneNumber, String password) {
        validateUtils.isAvailableEmailFormat(email);
        validateUtils.isAvailableLoginIdFormat(loginId);
        validateUtils.isAvailablePhoneNumberFormat(phoneNumber);
        validateUtils.isAvailablePasswordFormat(password);
    }
    private void isDuplicateLoginInfo(String email, String loginId, String phoneNumber) {
        if (memberRepository.existsByEmail(email)) {
            throw new MRAuthException(ResultCode.VALID_EMAIL_DUPLICATE);
        }
        if (memberRepository.existsByLoginId(loginId)) {
            throw new MRAuthException(ResultCode.VALID_LOGINID_DUPLICATE);
        }
        if (memberRepository.existsByPhoneNumber(phoneNumber)) {
            throw new MRAuthException(ResultCode.VALID_PHONENUMBER_DUPLICATE);
        }
        log.debug("[Signup] Login Info Duplication Check, OK");
    }

    private void saveMember(Member member) {
        memberRepository.save(member);
        log.debug("[Signup] Member register, OK");
    }


    private Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow(()->
                new MRMemberException(ResultCode.MEMBER_NOT_FOUND));
    }
}
