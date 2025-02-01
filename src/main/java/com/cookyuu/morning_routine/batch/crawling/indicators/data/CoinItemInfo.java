package com.cookyuu.morning_routine.batch.crawling.indicators.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinItemInfo {
    private String symbol;
    private String name;
    private double price;
    private double changePrice;
    private double changePct;
    private boolean hasPositivePrice;
    private LocalDate asOfDate;
}
