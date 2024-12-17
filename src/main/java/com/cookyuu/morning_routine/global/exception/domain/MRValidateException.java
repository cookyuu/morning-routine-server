package com.cookyuu.morning_routine.global.exception.domain;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.MRException;

public class MRValidateException extends MRException {
    public MRValidateException() {
        super(ResultCode.BAD_REQUEST);
    }

    public MRValidateException(ResultCode resultCode) {
        super(resultCode);
    }

    public MRValidateException(ResultCode resultCode, Throwable t) {
        super(resultCode, t);
    }

}
