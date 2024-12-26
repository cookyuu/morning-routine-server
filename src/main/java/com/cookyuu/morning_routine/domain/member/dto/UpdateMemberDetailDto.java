package com.cookyuu.morning_routine.domain.member.dto;

import com.cookyuu.morning_routine.domain.member.entity.OauthType;
import com.cookyuu.morning_routine.global.annotation.Enum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateMemberDetailDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String password;
        private String email;
        private String phoneNumber;
        private boolean validatedPhoneNumber;
        private boolean connectedOauth;
        @Enum(enumClass = OauthType.class, ignoreCase = true)
        private String oauthType;
    }
}
