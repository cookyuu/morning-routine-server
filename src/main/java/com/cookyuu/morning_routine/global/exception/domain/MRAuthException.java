package com.cookyuu.morning_routine.global.exception.domain;

import com.cookyuu.morning_routine.global.exception.MRException;
import com.cookyuu.morning_routine.global.code.ResultCode;

public class MRAuthException extends MRException {
    public MRAuthException() {
        super(ResultCode.BAD_REQUEST);
    }

    public MRAuthException(ResultCode resultCode) {
        super(resultCode);
    }

    public MRAuthException(ResultCode resultCode, Throwable t) {
        super(resultCode, t);
    }

}
