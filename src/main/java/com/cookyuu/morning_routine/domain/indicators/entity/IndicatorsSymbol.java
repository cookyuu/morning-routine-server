package com.cookyuu.morning_routine.domain.indicators.entity;

import com.cookyuu.morning_routine.global.exception.domain.MRCrawlingException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum IndicatorsSymbol {
    // 주가 지수
    KOSPI("kospi","코스피지수", Country.KOREA),
    KOSPI50("kospi-50", "코스피 50", Country.KOREA),
    KOSDAQ("kosdaq", "코스닥", Country.KOREA),
    DAWJONES("us-30","다우존스", Country.USA),
    SNP500("us-spx-500", "S&P 500", Country.USA),
    NASDAQ("nasdaq-composite","나스닥종합지수", Country.USA),
    RUSSELL2000("smallcap-2000", "러셀 2000", Country.USA),
    VIX("volatility-s-p-500","CBOE VIX", Country.USA),
    DAX("germany-30","DAX derived", Country.GER),
    JPN("japan-ni225","닛케이 derived", Country.JPN),
    CHNA50("ftse-china-a50","China A50", Country.CHN),
    HANGSEN("hang-sen-40", "항셍 derived", Country.HOK);

    private static final Map<String, IndicatorsSymbol> SYMBOL_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(IndicatorsSymbol::getName, Function.identity())));

    private String symbol;
    private String name;
    private Country country;

    public static IndicatorsSymbol getSymbol(String name) {
        return SYMBOL_MAP.getOrDefault(name, null);
    }

}
