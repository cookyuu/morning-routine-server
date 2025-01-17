package com.cookyuu.morning_routine.domain.stock.dto;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.entity.StockIndex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.time.LocalDate.now;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockInfoDto {
    private CrawlingInfo crawlingInfo = new CrawlingInfo();
    private CrawlingIndexInfo crawlingIndexInfo = new CrawlingIndexInfo();

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CrawlingInfo {
        private String symbol;
        private String name;
        private Country country;
        public void setCrawlingInfo(String symbol, String name, Country country) {
            this.symbol = symbol;
            this.name = name;
            this.country = country;
        }

        public Stock toEntity() {
            return Stock.builder()
                    .symbol(symbol)
                    .name(name)
                    .country(country)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CrawlingIndexInfo {
        private Long marketCap;
        private float closingPrice;
        private float priceChange;
        private float percentChange;
        private boolean hasPositiveStock;
        private LocalDate priceBaseDate;
        public void setCrawlingInfo (String marketCap, String closingPrice, String priceChange, String percentChange) {
            this.hasPositiveStock = !priceChange.contains("-");
            try {
                this.marketCap = Long.valueOf(marketCap.replace(",", ""));
            } catch (NumberFormatException e) {
                this.marketCap = null;
            }
            try {
                this.closingPrice = Float.parseFloat(closingPrice.replace("$","").replace(",",""));
            } catch (NumberFormatException e) {
                this.closingPrice = 0;
            }
            try {
                this.priceChange = Float.parseFloat(priceChange.replace("-","").replace(",",""));
            } catch (NumberFormatException e) {
                this.priceChange = 0;
            }
            try {
                this.percentChange = Float.parseFloat(percentChange.replace("%", "").replace("-", "").replace(",",""));
            } catch (NumberFormatException e) {
                this.percentChange = 0;
            }
            this.priceBaseDate = now();
        }

        public StockIndex toEntity(Stock stock) {
            return StockIndex.builder()
                    .marketCap(marketCap)
                    .oriClosingPrice(closingPrice)
                    .oriChangePrice(priceChange)
                    .oriChangePercent(percentChange)
                    .oriHasPositiveStock(hasPositiveStock)
                    .priceBaseDate(priceBaseDate)
                    .stock(stock)
                    .build();
        }
    }
}
