package com.cookyuu.morning_routine.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class IssueOtpCodeDto {
    
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String phoneNumber;
    }
}
