package com.cookyuu.morning_routine.domain.member.facade;

import com.cookyuu.morning_routine.domain.member.dto.UpdateMemberDetailDto;
import com.cookyuu.morning_routine.domain.member.dto.MemberDetailDto;
import com.cookyuu.morning_routine.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;

    public MemberDetailDto.Response getUserDetail(String loginId) {
        return memberService.getMemberDetail(loginId);
    }

    public void updateUserDetail(String loginId, UpdateMemberDetailDto.Request memberInfo) {
        memberService.updateMemberDetail(loginId, memberInfo);
    }
}
