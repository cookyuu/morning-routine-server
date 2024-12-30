package com.cookyuu.morning_routine.domain.stock.facade;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.service.StockCrawlingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockCrawlingFacade {

    private final StockCrawlingService stockCrawlingService;

    public void crawlingStock(Country country) throws JsonProcessingException {
        stockCrawlingService.crawlingStock(country);
    }
}
