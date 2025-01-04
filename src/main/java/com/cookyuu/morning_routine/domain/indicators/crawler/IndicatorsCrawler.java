package com.cookyuu.morning_routine.domain.indicators.crawler;

import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IndicatorsCrawler {
    List<IndicatorsInfoDto> crawlingIndicators() throws JsonProcessingException;
    IndicatorsType getIndicatorsType();
}
