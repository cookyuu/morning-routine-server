package com.cookyuu.morning_routine.domain.stock.facade;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.service.StockCrawlingService;
import com.cookyuu.morning_routine.domain.stock.service.StockIndexService;
import com.cookyuu.morning_routine.domain.stock.service.StockService;
import com.cookyuu.morning_routine.domain.stock.service.factory.StockCrawlingServiceFactory;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRStockException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockCrawlingFacade {

    private final StockCrawlingServiceFactory stockCrawlingServiceFactory;
    private final StockIndexService stockIndexService;
    private final StockService stockService;

    public void crawlingStock(Country country) throws JsonProcessingException {
        if (stockIndexService.isTodayCrawlingFinished(country)) {
            throw new MRStockException(ResultCode.CRAWLING_IS_FAIL, "금일 크롤링은 이미 완료되었습니다.");
        }
        StockCrawlingService stockCrawlingService = stockCrawlingServiceFactory.getService(country);
        stockCrawlingService.crawlingStock(country);
    }
}
