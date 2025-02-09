package com.cookyuu.morning_routine.domain.indicators.crawler.factory;

import com.cookyuu.morning_routine.domain.indicators.crawler.IndicatorsCrawler;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IndicatorsCrawlerFactory {
    private final Map<IndicatorsType, IndicatorsCrawler> indicatorsTypeIndicatorsCrawlorMap = new HashMap<>();
    public IndicatorsCrawlerFactory(List<IndicatorsCrawler> indicatorsCrawlers) {
        indicatorsCrawlers.forEach(crawler -> indicatorsTypeIndicatorsCrawlorMap.put(crawler.getIndicatorsType(), crawler));
    }

    public IndicatorsCrawler getCrawler(IndicatorsType indicatorsType) {
        return indicatorsTypeIndicatorsCrawlorMap.get(indicatorsType);
    }
}
