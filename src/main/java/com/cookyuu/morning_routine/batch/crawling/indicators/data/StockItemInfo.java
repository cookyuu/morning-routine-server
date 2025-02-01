package com.cookyuu.morning_routine.batch.crawling.indicators.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockItemInfo {
    private String name;
    private double endPrice;
    private double highestPrice;
    private double lowestPrice;
    private double changePrice;
    private double changePct;
    private boolean hasPositivePrice;
    private String asOfDate;
}
