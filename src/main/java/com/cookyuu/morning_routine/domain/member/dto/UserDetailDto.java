package com.cookyuu.morning_routine.domain.member.dto;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.member.entity.OauthType;
import com.cookyuu.morning_routine.domain.member.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDetailDto {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Response {
        private String name;
        private String loginId;
        private String email;
        private String phoneNumber;
        private boolean isConnectOauth;
        private OauthType oauthType;
        private RoleType roleType;

        public static Response from(Member member) {
            return Response.builder()
                    .name(member.getName())
                    .loginId(member.getLoginId())
                    .email(member.getEmail())
                    .phoneNumber(member.getPhoneNumber())
                    .isConnectOauth(member.isConnectOauth())
                    .oauthType(member.getOauthType())
                    .roleType(member.getRole())
                    .build();
        }
    }
}
