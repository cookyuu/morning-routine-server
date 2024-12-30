package com.cookyuu.morning_routine.domain.stock.service;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockCrawlingService {
    public void crawlingStock(Country country) throws JsonProcessingException;
}
