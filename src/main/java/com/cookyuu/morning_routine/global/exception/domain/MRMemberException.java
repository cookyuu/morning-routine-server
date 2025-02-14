package com.cookyuu.morning_routine.global.exception.domain;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.MRException;

public class MRMemberException extends MRException {
    public MRMemberException() {
        super(ResultCode.MEMBER_NOT_FOUND);
    }

    public MRMemberException(ResultCode resultCode) {
        super(resultCode);
    }

    public MRMemberException(ResultCode resultCode, String customMsg) {
        super(resultCode, customMsg);
    }

    public MRMemberException(ResultCode resultCode, Throwable t) {
        super(resultCode, t);
    }
}
