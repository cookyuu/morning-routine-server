package com.cookyuu.morning_routine.domain.stock.service.factory;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.service.StockCrawlingService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StockCrawlingServiceFactory {
    private final Map<Country, StockCrawlingService> countryStockCrawlingServiceMap = new HashMap<>();

    public StockCrawlingServiceFactory(List<StockCrawlingService> stockCrawlingServiceList) {
        stockCrawlingServiceList.forEach(service -> countryStockCrawlingServiceMap.put(service.getCountryType(), service));
    }
    public StockCrawlingService getService(Country country) {
        return countryStockCrawlingServiceMap.get(country);
    }
}
