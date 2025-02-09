package com.cookyuu.morning_routine.domain.stock.repository;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.entity.StockIndex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface StockIndexRepository extends JpaRepository<StockIndex, Long> {
    boolean existsByPriceBaseDateAndCountry(LocalDate today, Country country);
}
