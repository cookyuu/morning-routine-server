package com.cookyuu.morning_routine.domain.member.controller;

import com.cookyuu.morning_routine.domain.member.dto.UpdateMemberDetailDto;
import com.cookyuu.morning_routine.domain.member.dto.MemberDetailDto;
import com.cookyuu.morning_routine.domain.member.facade.MemberFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import com.cookyuu.morning_routine.global.security.jwt.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberFacade memberFacade;

    @GetMapping("/me")
    public ApiResponse<MemberDetailDto.Response> getMemberDetailMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ApiResponse.success(memberFacade.getUserDetail(userDetails.getLoginId()));
    }

    @PutMapping("/me")
    public ApiResponse<String> updateMemberDetailMe(@AuthenticationPrincipal CustomUserDetails userDetails, @Valid @RequestBody UpdateMemberDetailDto.Request memberInfo) {
         memberFacade.updateUserDetail(userDetails.getLoginId(), memberInfo);
        return ApiResponse.success("회원 정보 수정 완료.");
    }
}
