package com.cookyuu.morning_routine.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

public class LoginDto {

    @Getter
    public static class Request {
        private String loginId;
        private String password;
    }

    @Getter
    @Builder
    public static class Response {
        private String accessToken;
    }
}
