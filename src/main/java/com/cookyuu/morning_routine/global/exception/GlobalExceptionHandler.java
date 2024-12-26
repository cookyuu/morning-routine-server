package com.cookyuu.morning_routine.global.exception;

import com.cookyuu.morning_routine.global.dto.ApiResponse;
import com.cookyuu.morning_routine.global.code.ResultCode;
import io.lettuce.core.RedisException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

@Primary
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MRException.class)
    public ResponseEntity<ApiResponse<Object>> handleMRException(WebRequest request, MRException e) {
        String errMsg = "";
        if (e.getMessage() == null) {
            errMsg = e.getResultCode().getMessage();
        } else {
            errMsg = e.getMessage();
        }
        log.error("[MRException] {}", errMsg);
        ApiResponse response;
        if (e.getData() == null) {
            response = ApiResponse.failure(e.getResultCode(), errMsg);
        } else {
            response = ApiResponse.failure(e.getResultCode(), errMsg, e.getData());
        }

        return new ResponseEntity<>(response, e.getResultCode().getStatus());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentialsExceptionException(WebRequest request, BadCredentialsException e) {
        log.error("[BadCredentialsException] ", e);
        var response = ApiResponse.failure(ResultCode.AUTH_PASSWORD_UNMATCHED, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.AUTH_PASSWORD_UNMATCHED.getStatus());
    }

    @ExceptionHandler(value = RedisException.class)
    public ResponseEntity<ApiResponse<Object>> handleRedisException(WebRequest request, RedisException e) {
        log.error("[RedisException] ", e);
        var response = ApiResponse.failure(ResultCode.REDIS_COMMON_EXP, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.REDIS_COMMON_EXP.getStatus());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(WebRequest request, AccessDeniedException e) {
        log.error("[AccessDeniedException] ", e);
        var response = ApiResponse.failure(ResultCode.ACCESS_DENIED, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.ACCESS_DENIED.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(WebRequest request, MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException] ", e);
        var response = ApiResponse.failure(ResultCode.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoSuchElementException(WebRequest request, HttpRequestMethodNotSupportedException e) {
        log.error("[HttpRequestMethodNotSupportedException] ", e);
        var response = ApiResponse.failure(ResultCode.METHOD_NOT_ALLOWED, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.METHOD_NOT_ALLOWED.getStatus());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoSuchElementException(WebRequest request, NoSuchElementException e) {
        log.error("[NoSuchElementException] ", e);
        var response = ApiResponse.failure(ResultCode.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.NOT_FOUND.getStatus());
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<ApiResponse<Object>> handleSQLException(WebRequest request, SQLException e) {
        log.error("[SQLException] ", e);
        var response = ApiResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.INTERNAL_SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ApiResponse<Object>> handleIOException(WebRequest request, IOException e) {
        log.error("[IOException] ", e);
        var response = ApiResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.INTERNAL_SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<ApiResponse<Object>> handleIndexOutOfBoundsException(WebRequest request, IndexOutOfBoundsException e) {
        log.error("[IndexOutOfBoundsException] ", e);
        var response = ApiResponse.failure(ResultCode.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalStateException(WebRequest request, IllegalStateException e) {
        log.error("[IllegalArgumentException] ", e);
        var response = ApiResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.INTERNAL_SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(WebRequest request, IllegalArgumentException e) {
        log.error("[IllegalArgumentException] ", e);
        var response = ApiResponse.failure(ResultCode.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(value = HttpMessageConversionException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMessageConversionException(WebRequest request, HttpMessageConversionException e) {
        log.error("[HttpMessageConversionException] ", e);
        var response = ApiResponse.failure(ResultCode.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(WebRequest request, ConstraintViolationException e) {
        log.error("[ConstraintViolationException] ", e);
        var response = ApiResponse.failure(ResultCode.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintDateTimeParseException(WebRequest request, DateTimeParseException e) {
        log.error("[DateTimeParseException] ", e);
        var response = ApiResponse.failure(ResultCode.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResponse<Object>> handleNullPointerException(WebRequest request, NullPointerException e) {
        log.error("[NullPointerException] ", e);
        var response = ApiResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.INTERNAL_SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(WebRequest request, Exception e) {
        log.error("[Exception] ", e);
        var response = ApiResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(response, ResultCode.INTERNAL_SERVER_ERROR.getStatus());
    }
}
