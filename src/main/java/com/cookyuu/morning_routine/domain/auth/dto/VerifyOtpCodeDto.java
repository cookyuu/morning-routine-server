package com.cookyuu.morning_routine.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VerifyOtpCodeDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String phoneNumber;
        private String otpCode;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private boolean validatedPhoneNumber;
    }
}
