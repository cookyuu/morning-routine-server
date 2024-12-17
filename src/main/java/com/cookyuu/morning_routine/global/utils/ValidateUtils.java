package com.cookyuu.morning_routine.global.utils;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ValidateUtils {
    /*   @앞 문자 : 소문자, 숫자, -. 가능 || @뒷 문자 : 하나 이상 라벨 존재 */
    String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    /* 패스워드 : 숫자, 대소문자, 특수문자 최소한 1개, 8~15자리 */
    String passwordRegex = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
    /* 핸드폰번호 : 앞3자리 - 중간 3or4자리 - 마지막 4자리 */
    String phoneNumberRegex = "^\\d{3}-\\d{3,4}-\\d{4}$";
    /* 유저아이디 : 공백X, 알파벳으로 시작, 6~14자리 */
    String loginIdRegex = "^(?!.*\\s)[a-zA-Z][a-zA-Z0-9._-]{5,13}$";

    public void isAvailableEmailFormat(String email) {
        log.debug("[ValidEmailFormat] Validate email format. email : {}", email);

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new MRValidateException(ResultCode.VALID_EMAIL_FORMAT);
        }
    }

    public void isAvailablePhoneNumberFormat(String phoneNumber) {
        Pattern pattern = Pattern.compile(phoneNumberRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            log.error("[ValidPhoneNumber] This Phone Number format is unAvailable, PhoneNumber : {}", phoneNumber);
            throw new MRValidateException(ResultCode.VALID_PHONENUMBER_FORMAT);
        }
        log.debug("[ValidPhoneNumberFormat] Validate phoneNumber format. OK!!,  phone number : {} ", phoneNumber);
    }

    public void isAvailablePasswordFormat(String password) {
        log.debug("[ValidPasswordFormat] Validate password format. ");
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            log.error("[ValidPhoneNumber] This Password format is unAvailable");
            throw new MRValidateException(ResultCode.VALID_PASSWORD_FORMAT);
        }
    }

    public void isAvailableLoginIdFormat(String loginId) {
        Pattern pattern = Pattern.compile(loginIdRegex);
        Matcher matcher = pattern.matcher(loginId);
        if (!matcher.matches()) {
            log.error("[ValidPhoneNumber] This Login ID format is unAvailable. LoginId : {}", loginId);
            throw new MRValidateException(ResultCode.VALID_LOGINID_FORMAT);
        }
        log.info("[ValidUserIdFormat] Validate userId format. OK!!, LoginId : {}", loginId);
    }

}
