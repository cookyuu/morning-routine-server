package com.cookyuu.morning_routine.domain.auth.service;

import com.cookyuu.morning_routine.domain.auth.dto.IssueOtpCodeDto;
import com.cookyuu.morning_routine.domain.auth.dto.VerifyOtpCodeDto;
import com.cookyuu.morning_routine.global.code.RedisKeyCode;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRAuthException;
import com.cookyuu.morning_routine.global.utils.AuthUtils;
import com.cookyuu.morning_routine.global.utils.RedisUtils;
import com.cookyuu.morning_routine.global.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    @Value("${auth.otp.exp}")
    String otpExpire;

    private final AuthUtils authUtils;
    private final RedisUtils redisUtils;
    private final ValidateUtils validateUtils;

    public String issueOtpCode(IssueOtpCodeDto.Request issueInfo) {
        String phoneNumber = issueInfo.getPhoneNumber();
        String countKey = RedisKeyCode.OTP_COUNT.getSeparator()+phoneNumber;
        String otpKey = RedisKeyCode.OTP_CODE.getSeparator()+phoneNumber;
        validateUtils.isAvailablePhoneNumberFormat(phoneNumber);
        String otpCode = authUtils.createOptCode();
        countSendOtp(countKey);
        saveOtpCodeInCache(otpKey, otpCode);
        log.debug("[Auth] Issue otp code, OK. ");
        return otpCode;
    }

    public void rollbackIssueOtpCode(IssueOtpCodeDto.Request issueInfo) {
        String countKey = RedisKeyCode.OTP_COUNT.getSeparator()+issueInfo.getPhoneNumber();
        String otpKey = RedisKeyCode.OTP_CODE.getSeparator()+issueInfo.getPhoneNumber();
        decCountSendOtp(countKey);
        deleteOtpCodeInCache(otpKey);
        log.info("[Auth] Rollback Issue Otp Code. ");
    }

    public VerifyOtpCodeDto.Response verifyOtpCode(VerifyOtpCodeDto.Request otpInfo) {
        String phoneNumber = otpInfo.getPhoneNumber();
        validateUtils.isAvailablePhoneNumberFormat(phoneNumber);

        String otpCode = otpInfo.getOtpCode();
        String key = RedisKeyCode.OTP_CODE.getSeparator()+phoneNumber;
        String cacheOtpCode = redisUtils.getData(key);
        if (cacheOtpCode == null) {
            throw new MRAuthException(ResultCode.AUTH_OTP_EXPIRED);
        }
        if (otpCode.equals(cacheOtpCode)) {
            return VerifyOtpCodeDto.Response.builder().validatedPhoneNumber(true).build();
        } else {
            throw new MRAuthException(ResultCode.AUTH_OTP_UNMATCHED, VerifyOtpCodeDto.Response.builder().validatedPhoneNumber(false).build());
        }

    }

    private void countSendOtp(String key) {
        int dayLimit = 5;
        long expireTime = authUtils.calculateNowToMidnight();

        Long currentCount = redisUtils.increaseCount(key);
        if (currentCount == 1) {
            redisUtils.setExpire(key, expireTime);
        }
        if (currentCount > dayLimit) {
            throw new MRAuthException(ResultCode.AUTH_OTP_DAILY_LIMIT);
        }
    }

    private void saveOtpCodeInCache(String key, String otpCode) {
        redisUtils.setDataExpire(key, otpCode, Long.parseLong(otpExpire));
        log.debug("[Auth] Save otp code in redis cache. ");
    }

    private void decCountSendOtp(String key) {
        Long currentCount = redisUtils.decrement(key);
        if (currentCount != null && currentCount <= 0) {
            redisUtils.deleteData(key);
        }
    }

    private void deleteOtpCodeInCache(String key) {
        redisUtils.deleteData(key);
    }
}
