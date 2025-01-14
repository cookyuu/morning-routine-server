package com.cookyuu.morning_routine.global.exception.domain;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.MRException;

public class MRStockException extends MRException {
    public MRStockException() {
        super(ResultCode.STOCK_NOT_FOUND);
    }

    public MRStockException(ResultCode resultCode) {
        super(resultCode);
    }

    public MRStockException(ResultCode resultCode, String customMsg) {
        super(resultCode, customMsg);
    }

    public MRStockException(ResultCode resultCode, Throwable t) {
        super(resultCode, t);
    }
}
