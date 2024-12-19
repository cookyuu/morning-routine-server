package com.cookyuu.morning_routine.domain.member.controller;

import com.cookyuu.morning_routine.domain.member.dto.UserDetailDto;
import com.cookyuu.morning_routine.domain.member.facade.MemberFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import com.cookyuu.morning_routine.global.security.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberFacade memberFacade;

    @GetMapping("/me")
    public ApiResponse<UserDetailDto.Response> getUserDetailMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ApiResponse.success(memberFacade.getUserDetail(userDetails.getLoginId()));
    }
}
