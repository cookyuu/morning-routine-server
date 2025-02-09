package com.cookyuu.morning_routine.global.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthUtils {
    private final PasswordEncoder passwordEncoder;

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void checkPassword(String memberPw, String reqPw) {
        if (!passwordEncoder.matches(reqPw, memberPw)) {
            log.error("[Login] Login fail, password is not matched. ");
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
    }

    public String createOptCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(888888) + 111111);
    }

    public long calculateNowToMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.toLocalDate().atTime(LocalTime.MAX).plusSeconds(1);
        return Duration.between(now, midnight).getSeconds();
    }
}