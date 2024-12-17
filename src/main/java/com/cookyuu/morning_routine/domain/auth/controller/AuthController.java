package com.cookyuu.morning_routine.domain.auth.controller;

import com.cookyuu.morning_routine.domain.auth.dto.LoginDto;
import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.auth.service.AuthService;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginDto.Response> login(@RequestBody LoginDto.Request loginInfo) {
        LoginDto.Response res = authService.login(loginInfo);
        return ApiResponse.success(res);
    }

    @PostMapping("/signup")
    public ApiResponse<String> signup(@RequestBody SignupDto.Request signupInfo) {
        authService.signup(signupInfo);
        return ApiResponse.success("회원가입 성공");
    }
}
