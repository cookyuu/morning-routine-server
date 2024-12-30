package com.cookyuu.morning_routine.batch.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CrawlingStockInfo {
    private String symbol;
    private String name;
    private String lastsale;
    private String netchange;
    private String pctchange;
    private String marketCap;
    private String url;
}
