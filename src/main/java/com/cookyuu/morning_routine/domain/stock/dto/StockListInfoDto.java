package com.cookyuu.morning_routine.domain.stock.dto;


import com.cookyuu.morning_routine.domain.stock.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WishStockInfoDto {
    private String symbol;
    private String name;
    private Country country;
    private Long marketCap;
    private float oriClosingPrice;
    private float oriPriceChange;
    private float oriPercentChange;
    private boolean oriHasPositiveStock;
    private boolean afterLoadCompleted;
    private float afterClosingPrice;
    private float afterChangePrice;
    private float afterChangePercent;
    private boolean afterHasPositiveStock;
    private LocalDate priceBaseDate;
}
