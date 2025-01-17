package com.cookyuu.morning_routine.domain.stock.dto;


import com.cookyuu.morning_routine.domain.stock.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockListInfoDto {
    private String symbol;
    private String name;
    private Country country;
    private Long marketCap;
    private LocalDate priceBaseDate;
    private boolean oriHasPositiveStock;
    private float oriClosingPrice;
    private float oriPercentChange;
    private float oriPriceChange;
    private boolean afterLoadCompleted;
    private boolean afterHasPositiveStock;
    private float afterClosingPrice;
    private float afterChangePrice;
    private float afterChangePercent;
}
