package com.cookyuu.morning_routine.domain.stock.service;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.entity.StockIndex;
import com.cookyuu.morning_routine.domain.stock.repository.StockIndexRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockIndexService {
    private final StockIndexRepository stockIndexRepository;

    @Transactional
    public void saveAll(List<StockIndex> stockIndexList) {
        stockIndexRepository.saveAll(stockIndexList);
        log.info("[StockIndex] Save All process complete, cnt : {}", stockIndexList.size());
    }

    @Transactional(readOnly = true)
    public boolean isTodayCrawlingFinished(Country country) {
        LocalDate today = LocalDate.now();
        return stockIndexRepository.existsByPriceBaseDateAndCountry(today, country);
    }
}
