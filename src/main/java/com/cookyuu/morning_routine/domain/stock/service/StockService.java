package com.cookyuu.morning_routine.domain.stock.service;

import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.repository.StockRepository;
import com.cookyuu.morning_routine.global.exception.domain.MRStockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    @Transactional
    public void saveAll(List<Stock> stockList) {
        stockRepository.saveAll(stockList);
        log.info("[Stock] Save All process complete, cnt : {}", stockList.size());
    }

    public Stock findBySymbolNullable(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    public Stock findBySymbol(String symbol) {
        return stockRepository.findById(symbol).orElseThrow(MRStockException::new);
    }
}
