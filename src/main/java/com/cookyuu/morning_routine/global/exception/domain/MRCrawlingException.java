package com.cookyuu.morning_routine.global.exception.domain;

import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.MRException;

public class MRCrawlingException extends MRException {
    public MRCrawlingException() {
        super(ResultCode.CRAWLING_IS_FAIL);
    }

    public MRCrawlingException(ResultCode resultCode) {
        super(resultCode);
    }

    public MRCrawlingException(ResultCode resultCode, String customMsg) {
        super(resultCode, customMsg);
    }

    public MRCrawlingException(ResultCode resultCode, Throwable t) {
        super(resultCode, t);
    }

    public MRCrawlingException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }
}
