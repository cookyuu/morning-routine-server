package com.cookyuu.morning_routine.domain.region.facade;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.member.service.MemberService;
import com.cookyuu.morning_routine.domain.region.dto.RegisterInterestRegionDto;
import com.cookyuu.morning_routine.domain.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionFacade {
    private final MemberService memberService;
    private final RegionService regionService;

    public void registerInterestRegion(RegisterInterestRegionDto regionInfo, Long userId) {
        Member member = memberService.getMemberById(userId);
        regionService.registerInterestRegion(regionInfo, member);
    }
}
