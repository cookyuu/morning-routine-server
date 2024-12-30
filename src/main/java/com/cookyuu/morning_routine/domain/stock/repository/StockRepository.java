package com.cookyuu.morning_routine.domain.stock.repository;

import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findBySymbol(String symbol);
}
