package com.cookyuu.morning_routine.domain.member.facade;

import com.cookyuu.morning_routine.domain.member.dto.UserDetailDto;
import com.cookyuu.morning_routine.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;

    public UserDetailDto.Response getUserDetail(String loginId) {
        return memberService.getMemberDetail(loginId);
    }
}
