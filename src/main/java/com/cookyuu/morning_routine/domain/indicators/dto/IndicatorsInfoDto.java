package com.cookyuu.morning_routine.domain.indicators.dto;

import com.cookyuu.morning_routine.domain.indicators.entity.Country;
import com.cookyuu.morning_routine.domain.indicators.entity.Indicators;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsIndex;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorsInfoDto {
    private IndicatorsInfo indicatorsInfo;
    private IndexInfo indexInfo;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IndicatorsInfo {
        private String symbol;
        private String name;
        private Country country;
        private IndicatorsType indicatorsType;

        public Indicators toEntity() {
            return Indicators.builder()
                    .symbol(symbol)
                    .name(name)
                    .country(country)
                    .indicatorsType(indicatorsType)
                    .build();
        }
        public void setIndicatorsInfo(String symbol, String name, Country country, IndicatorsType indicatorsType) {
            this.symbol = symbol;
            this.name = name;
            this.country = country;
            this.indicatorsType = indicatorsType;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IndexInfo {
        private double netChange;
        private double percentChange;
        private double price;
        private boolean hasPositivePrice;
        private LocalDate asOfDate;

        public IndicatorsIndex toEntity(Indicators indicators) {
            return IndicatorsIndex.builder()
                    .netChange(netChange)
                    .percentChange(percentChange)
                    .price(price)
                    .hasPositivePrice(hasPositivePrice)
                    .asOfDate(asOfDate)
                    .indicators(indicators)
                    .build();
        }

        public void setIndexInfo(double netChange, double percentChange, double price, boolean hasPositivePrice, LocalDate asOfDate) {
            this.netChange = netChange;
            this.percentChange = percentChange;
            this.price = price;
            this.hasPositivePrice = hasPositivePrice;
            this.asOfDate = asOfDate;
        }
    }

}
