package com.cookyuu.morning_routine.domain.indicators.service.factory;

import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.service.IndicatorsCrawlingService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IndicatorsCrawlingServiceFactory {
    private final Map<IndicatorsType, IndicatorsCrawlingService> indicatorsTypeIndicatorsCrawlingServiceMap = new HashMap<>();

    public IndicatorsCrawlingServiceFactory(List<IndicatorsCrawlingService> indicatorsCrawlingServiceList) {
        indicatorsCrawlingServiceList.forEach(service -> indicatorsTypeIndicatorsCrawlingServiceMap.put(service.getIndicatorsType(), service));
    }
    public IndicatorsCrawlingService getService(IndicatorsType indicatorsType) {
        return indicatorsTypeIndicatorsCrawlingServiceMap.get(indicatorsType);
    }
}