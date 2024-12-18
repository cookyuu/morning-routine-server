package com.cookyuu.morning_routine.domain.auth.controller;

import com.cookyuu.morning_routine.domain.auth.dto.LoginDto;
import com.cookyuu.morning_routine.domain.auth.dto.SignupDto;
import com.cookyuu.morning_routine.domain.auth.facade.AuthFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ApiResponse<LoginDto.Response> login(@RequestBody LoginDto.Request loginInfo, HttpServletResponse response) {
        LoginDto.Response res = authFacade.login(loginInfo, response);
        return ApiResponse.success(res);
    }

    @PostMapping("/signup")
    public ApiResponse<String> signup(@RequestBody SignupDto.Request signupInfo) {
        authFacade.signup(signupInfo);
        return ApiResponse.success("회원가입 성공");
    }
}
