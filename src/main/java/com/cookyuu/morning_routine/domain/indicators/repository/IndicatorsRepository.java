package com.cookyuu.morning_routine.domain.indicators.repository;

import com.cookyuu.morning_routine.domain.indicators.entity.Indicators;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorsRepository extends JpaRepository<Indicators, String> {
    Indicators findBySymbol(String symbol);
}
