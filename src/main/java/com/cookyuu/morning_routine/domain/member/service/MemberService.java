package com.cookyuu.morning_routine.domain.member.service;

import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.member.repository.MemberRepository;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void saveMember(Member member) {
        memberRepository.save(member);
        log.debug("[Signup] Member register, OK");
    }

    public void isDuplicateLoginInfo(String email, String loginId, String phoneNumber) {
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
}
