package com.cookyuu.morning_routine.batch.crawling.indicators;

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
    private float endPrice;
    private float highestPrice;
    private float lowestPrice;
    private float changePrice;
    private float changePct;
    private boolean hasPositivePrice;
    private String asOfDate;
}
