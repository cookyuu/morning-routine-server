package com.cookyuu.morning_routine.global.exception;

import com.cookyuu.morning_routine.global.code.ResultCode;
import lombok.Getter;

@Getter
public class MRException extends RuntimeException {
    private final ResultCode resultCode;
    private String message;

    private String[] args;
    private Object data;

    protected MRException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    protected MRException(ResultCode resultCode, String customMsg) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.message = customMsg;
    }

    protected MRException(ResultCode resultCode, Object data, String[] args) {
        super(resultCode.getMessage());
        this.data = data;
        this.args = args;
        this.resultCode = resultCode;
    }

    protected MRException(ResultCode resultCode, Throwable t) {
        super(t);
        this.resultCode = resultCode;
    }

    protected MRException(ResultCode resultCode, Throwable t, String customMsg) {
        super(t);
        this.resultCode = resultCode;
        this.message = customMsg;
    }

    protected MRException(ResultCode resultCode, Object data) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.data = data;
    }

}
