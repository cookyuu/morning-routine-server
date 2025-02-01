package com.cookyuu.morning_routine.domain.indicators.facade;

import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.domain.indicators.service.IndicatorsCrawlingService;
import com.cookyuu.morning_routine.domain.indicators.crawler.IndicatorsCrawler;
import com.cookyuu.morning_routine.domain.indicators.crawler.factory.IndicatorsCrawlerFactory;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.service.factory.IndicatorsCrawlingServiceFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndicatorsCrawlingFacade {
    private final IndicatorsCrawlingServiceFactory indicatorsCrawlingServiceFactory;
    private final IndicatorsCrawlerFactory indicatorsCrawlerFactory;

    public void crawlingIndicators(IndicatorsType indicatorsType) throws JsonProcessingException {
        IndicatorsCrawler indicatorsCrawler = indicatorsCrawlerFactory.getCrawler(indicatorsType);
        IndicatorsCrawlingService indicatorsCrawlingService = indicatorsCrawlingServiceFactory.getService(indicatorsType);
        List<IndicatorsInfoDto> dataList = indicatorsCrawler.crawlingIndicators();
        indicatorsCrawlingService.saveCrawlingData(dataList);
    }
}
