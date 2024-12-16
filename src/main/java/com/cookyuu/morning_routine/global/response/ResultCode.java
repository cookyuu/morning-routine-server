package com.cookyuu.morning_routine.global.response;

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
    REDISSON_COMMON_EXP(HttpStatus.TOO_MANY_REQUESTS, "C-012", "처리할 요청이 너무 많습니다. 다시 시도해주세요. ");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ResultCode (HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
