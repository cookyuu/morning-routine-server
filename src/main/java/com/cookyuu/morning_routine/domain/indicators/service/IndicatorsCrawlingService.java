package com.cookyuu.morning_routine.domain.indicators.service;

import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;

import java.util.List;

public interface IndicatorsCrawlingService {
    void saveCrawlingData(List<IndicatorsInfoDto> dataList);
    IndicatorsType getIndicatorsType();
}
