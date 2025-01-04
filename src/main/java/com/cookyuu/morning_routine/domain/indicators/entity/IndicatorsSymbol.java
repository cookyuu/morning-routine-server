package com.cookyuu.morning_routine.domain.indicators.entity;

import com.cookyuu.morning_routine.global.exception.domain.MRCrawlingException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    HANGSEN("hang-sen-40", "항셍 derived", Country.HOK),

    // 환율
    JPY("USD/JPY", "달러/엔", Country.COMMON),
    CNY("USD/CNY", "달러/위안", Country.COMMON),
    KRW("USD/KRW", "달러/원", Country.COMMON),
    EUR("USD/EUR", "달러/유로", Country.COMMON),

    // 코인
    BTC("BTC", "비트코인", Country.COMMON),
    ETH("ETH", "이더리움", Country.COMMON),
    XRP("XRP", "리플", Country.COMMON),
    USDT("USTD", "테더", Country.COMMON),
    SOL("SOL", "솔라나", Country.COMMON),
    DOGE("DOGE", "도지코인", Country.COMMON);

    private static final Map<String, IndicatorsSymbol> SYMBOL_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(IndicatorsSymbol::getName, Function.identity())));

    private String symbol;
    private String name;
    private Country country;

    public static IndicatorsSymbol getSymbolByName(String name) {
        return SYMBOL_MAP.getOrDefault(name, null);
    }

    public static List<IndicatorsSymbol> getExchangeSymbol() {
        List<IndicatorsSymbol> symbols = new ArrayList<>();
        symbols.add(JPY);
        symbols.add(CNY);
        symbols.add(KRW);
        symbols.add(EUR);
        return symbols;
    }

    public static List<IndicatorsSymbol> getCoinSymbol() {
        List<IndicatorsSymbol> symbols = new ArrayList<>();
        symbols.add(BTC);
        symbols.add(ETH);
        symbols.add(XRP);
        symbols.add(USDT);
        symbols.add(SOL);
        symbols.add(DOGE);
        return symbols;
    }

}
