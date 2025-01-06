package com.cookyuu.morning_routine.global.exception.domain;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.MRException;

public class MRRegionException extends MRException {
    public MRRegionException() {
        super(ResultCode.REGION_NOT_FOUND);
    }

    public MRRegionException(ResultCode resultCode) {
        super(resultCode);
    }

    public MRRegionException(ResultCode resultCode, String customMsg) {
        super(resultCode, customMsg);
    }

    public MRRegionException(ResultCode resultCode, Throwable t) {
        super(resultCode, t);
    }
}
