package com.cookyuu.morning_routine.batch.crawling.indicators;

import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsSymbol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialItemInfo {
    private IndicatorsSymbol symbol;
    private double price;
    private double changePrice;
    private double changePct;
    private boolean hasPositivePrice;
    private LocalDate asOfDate;
}
