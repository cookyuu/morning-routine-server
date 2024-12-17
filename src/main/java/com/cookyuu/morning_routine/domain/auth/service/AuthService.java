package com.cookyuu.morning_routine.domain.auth.service;


import com.cookyuu.morning_routine.domain.auth.dto.LoginDto;

public interface AuthService {
    public LoginDto.Response login(Object loginInfo);
    public void signup(Object signupInfo);
}
