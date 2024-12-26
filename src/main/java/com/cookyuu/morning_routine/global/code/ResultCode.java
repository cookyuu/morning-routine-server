package com.cookyuu.morning_routine.global.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultCode {
    // Common Success
    SUCCESS(HttpStatus.OK,"0000","성공!"),
    CREATED(HttpStatus.CREATED,"0000", "성공!"),

    // Common Error
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"C-001", "잘못 요청값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C-002", "허용되지 않은 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"C-004", "서버 에러 입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST,"C-005", "잘못된 유형의 값입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "C-006", "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,"C-007", "데이터를 찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C-008", "잘못된 요청입니다."),
    BAD_CREDENTIAL(HttpStatus.UNAUTHORIZED, "C-009", "자격 증명에 실패했습니다."),
    REQUEST_DATA_ISNULL(HttpStatus.BAD_REQUEST,"C-010" , "요청 데이터가 모두 NULL 입니다."),
    REDIS_COMMON_EXP(HttpStatus.INTERNAL_SERVER_ERROR,"C-011","Redis Exception 발생. "),
    REDISSON_COMMON_EXP(HttpStatus.TOO_MANY_REQUESTS, "C-012", "처리할 요청이 너무 많습니다. 다시 시도해주세요. "),

    // JWT - ERROR
    JWT_SECURE_EXP(HttpStatus.FORBIDDEN, "J-001", "토큰 보안 오류 발생. "),
    JWT_INVALID_TOKEN(HttpStatus.BAD_REQUEST, "J-002", "유효하지 않은 토큰입니다."),
    JWT_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "J-003", "토큰 유효기한이 만료되었습니다."),
    JWT_UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "J-004", "지원하지 않는 토큰입니다."),
    JWT_EMPTY_TOKEN(HttpStatus.BAD_REQUEST, "J-005", "토큰 데이터가 비어있습니다."),
    JWT_ALREADY_LOGOUT(HttpStatus.UNAUTHORIZED, "J-006", "이미 로그아웃된 사용자입니다."),

    // Auth
    AUTH_PASSWORD_UNMATCHED(HttpStatus.BAD_REQUEST, "A-001", "로그인 실패, 패스워드가 일치하지 않습니다."),
    AUTH_OTP_DAILY_LIMIT(HttpStatus.BAD_REQUEST, "A-002", "일일 OTP 이용한도를 초과했습니다. "),
    SMS_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "A-003" , "SMS 전송 실패. "),
    AUTH_OTP_EXPIRED(HttpStatus.UNAUTHORIZED, "A-004", "OTP CODE가 만료됐습니다. 다시 발급해주세요. " ),
    AUTH_OTP_UNMATCHED(HttpStatus.UNAUTHORIZED, "A-005", "인증번호가 일치하지 않습니다."),

    // Member
    VALID_EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST, "M-001", "이메일 유효성 검증 실패. 이미 등록된 이메일입니다."),
    VALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "M-002", "이메일 유효성 검증 실패. 잘못된 이메일 형식입니다."),
    VALID_PHONENUMBER_DUPLICATE(HttpStatus.BAD_REQUEST,"M-003", "핸드폰 번호 유효성 검증 실패. 이미 등록된 핸드폰 번호입니다."),
    VALID_PHONENUMBER_FORMAT(HttpStatus.BAD_REQUEST, "M-004", "핸드폰 번호 유효성 검증 실패. 잘못된 형식의 핸드폰 번호입니다."),
    VALID_LOGINID_DUPLICATE(HttpStatus.BAD_REQUEST,"M-005", "로그인 아이디 유효성 검증 실패. 이미 등록된 아이디입니다."),
    VALID_LOGINID_FORMAT(HttpStatus.BAD_REQUEST, "M-006", "로그인 아이디 유효성 검증 실패. 잘못된 아이디 형식입니다."),
    VALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, "M-007", "패스워드 유효성 검증 실패. 잘못된 패스워드 형식입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M-008", "해당 유저를 찾을 수 없습니다."),
    MEMBER_DETAIL_UPDATE_FAIL(HttpStatus.BAD_REQUEST, "M-009", "회원 정보 수정 실패.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    ResultCode (HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
