package com.cookyuu.morning_routine.domain.stock.repository;

import com.cookyuu.morning_routine.domain.stock.entity.StockIndex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockIndexRepository extends JpaRepository<StockIndex, Long> {
}
