package com.cookyuu.morning_routine.domain.member.service;

import com.cookyuu.morning_routine.domain.auth.dto.JWTUserInfo;
import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.member.dto.UpdateMemberDetailDto;
import com.cookyuu.morning_routine.domain.member.dto.MemberDetailDto;
import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.member.entity.OauthType;
import com.cookyuu.morning_routine.domain.member.repository.MemberRepository;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRAuthException;
import com.cookyuu.morning_routine.global.exception.domain.MRMemberException;
import com.cookyuu.morning_routine.global.utils.AuthUtils;
import com.cookyuu.morning_routine.global.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthUtils authUtils;
    private final ValidateUtils validateUtils;

    @Transactional(readOnly = true)
    public JWTUserInfo checkLoginCredentials(String loginId, String password) {
        Member member = findByLoginId(loginId);
        authUtils.checkPassword(member.getPassword(), password);
        JWTUserInfo userInfo = new JWTUserInfo();
        userInfo.of(member);
        return userInfo;
    }

    @Transactional
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

    @Transactional
    public void updateMemberDetail(String loginId, UpdateMemberDetailDto.Request memberInfo) {
        String email = memberInfo.getEmail();
        String password = memberInfo.getPassword();
        String oauthType = memberInfo.getOauthType();
        String phoneNumber = memberInfo.getPhoneNumber();
        boolean connectedOauth = memberInfo.isConnectedOauth();
        boolean validatedPhoneNumber = memberInfo.isValidatedPhoneNumber();

        if (email.isBlank() && password.isBlank() && oauthType.isBlank() && connectedOauth && validatedPhoneNumber) {
            throw new MRMemberException(ResultCode.MEMBER_DETAIL_UPDATE_FAIL, "회원 정보 수정 요청 데이터가 없습니다.");
        }
        if (!phoneNumber.isBlank() && !validatedPhoneNumber) {
            throw new MRMemberException(ResultCode.MEMBER_DETAIL_UPDATE_FAIL, "회원 정보 수정 실패. 핸드폰 인증이 필요합니다.");
        }

        Member member = findByLoginId(loginId);
        validateUpdateInfo(email, phoneNumber, password);

        if (!phoneNumber.isBlank()) {
            member.setPhoneNumber(phoneNumber);
        }
        if (!password.isBlank()) {
            member.setPassword(authUtils.encryptPassword(password));
        }
        if (!email.isBlank() && !oauthType.isBlank() && connectedOauth) {
            member.setOauth(email, OauthType.valueOf(oauthType));
        }
        log.info("[Member] Update member detail. OK, loginId : {}", loginId);
    }

    public MemberDetailDto.Response getMemberDetail(String loginId) {
        Member member = findByLoginId(loginId);
        return MemberDetailDto.Response.from(member);
    }

    private void validateSignupInfo(String email, String loginId, String phoneNumber, String password) {
        if (!email.isBlank()) {
            validateUtils.isAvailableEmailFormat(email);
        }
        validateUtils.isAvailableLoginIdFormat(loginId);
        validateUtils.isAvailablePhoneNumberFormat(phoneNumber);
        validateUtils.isAvailablePasswordFormat(password);
    }

    private void validateUpdateInfo(String email, String phoneNumber, String password) {
        if (!email.isBlank()) {
            validateUtils.isAvailableEmailFormat(email);
        }
        if (!phoneNumber.isBlank()) {
            validateUtils.isAvailablePhoneNumberFormat(phoneNumber);
        }
        if (!password.isBlank()) {
            validateUtils.isAvailablePasswordFormat(password);
        }
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

    public Member getMemberById(Long userId) {
        return memberRepository.findById(userId).orElseThrow(MRMemberException::new);
    }
}
