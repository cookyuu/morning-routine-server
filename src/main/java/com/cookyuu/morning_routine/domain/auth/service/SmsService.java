package com.cookyuu.morning_routine.domain.auth.service;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsService {

    public void sendSms(String phoneNumber, String otpCode) {
        try {
            // SMS API 선정 후 개발

        } catch (Exception e) {
            throw new MRAuthException(ResultCode.SMS_SEND_FAIL);
        }
        log.debug("[Auth] Send auth code (SMS), OK. ");
    }
}
