package com.cookyuu.morning_routine.global.dto;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ApiResponse<T> {
    private final ResultCode resultCode;
    private final String code;
    private final String message;
    private final T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final PageResponse page;

    private ApiResponse(ResultCode code, String message, T data, PageResponse page) {
        this.resultCode = code;
        this.code = code.getCode();
        this.message = message;
        this.data = data;
        this.page = page;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(
                ResultCode.SUCCESS,
                ResultCode.SUCCESS.getMessage(),
                null,
                null
        );
    }

    public static <T> ApiResponse<T> success(ResultCode resultCode){
        return new ApiResponse<>(
                resultCode,
                resultCode.getMessage(),
                null,
                null
        );
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                ResultCode.SUCCESS,
                ResultCode.SUCCESS.getMessage(),
                data,
                null
        );
    }

    public static <T> ApiResponse<T> success(ResultCode resultCode, T data) {
        return new ApiResponse<>(
                resultCode,
                resultCode.getMessage(),
                data,
                null
        );
    }

    public static <T> ApiResponse<List<T>> success(List<T> data) {
        return new ApiResponse<>(
                ResultCode.SUCCESS,
                ResultCode.SUCCESS.getMessage(),
                data,
                null
        );
    }

    public static <T> ApiResponse<List<T>> success(Page<T> data) {
        return new ApiResponse<>(
                ResultCode.SUCCESS,
                ResultCode.SUCCESS.getMessage(),
                data.getContent(),
                PageResponse.from(data)
        );
    }

    public static <T> ApiResponse<T> created() {
        return new ApiResponse<>(
                ResultCode.CREATED,
                ResultCode.CREATED.getMessage(),
                null,
                null
        );
    }

    public static <T> ApiResponse<T> created(ResultCode resultCode) {
        return new ApiResponse<>(
                resultCode,
                resultCode.getMessage(),
                null,
                null
        );
    }

    public static<T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(
                ResultCode.CREATED,
                ResultCode.CREATED.getMessage(),
                data,
                null
        );
    }

    public static <T> ApiResponse<T> failure(ResultCode resultCode) {
        return new ApiResponse<>(
                resultCode,
                resultCode.getMessage(),
                null,
                null
        );
    }

    public static <T> ApiResponse<T> failure(ResultCode resultCode, String message) {
        return new ApiResponse<>(
                resultCode,
                message,
                null,
                null
        );
    }

    @Getter
    @AllArgsConstructor
    public static class PageResponse {
        private final int number;
        private final int size;
        private final int totalCount;

        public static PageResponse from(Page<?> page) {
            return new PageResponse(
                    page.getNumber(),
                    page.getSize(),
                    (int)page.getTotalElements()
            );
        }
    }
}
