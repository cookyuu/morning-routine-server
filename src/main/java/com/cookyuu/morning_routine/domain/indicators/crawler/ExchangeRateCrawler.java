package com.cookyuu.morning_routine.domain.indicators.crawler;

import com.cookyuu.morning_routine.batch.crawling.indicators.data.ExchangeItemInfo;
import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsSymbol;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.global.utils.RestClientUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRateCrawler implements IndicatorsCrawler {

    @Value("${collector.exchange.url}")
    private String exchangeRateIndicatorsUrl;

    private final RestClientUtils restClientUtils;

    @Override
    public List<IndicatorsInfoDto> crawlingIndicators() throws JsonProcessingException {
        ExchangeItemInfo crawlingData = getExchangeCrawlingData();
        return convertToIndicatorsInfoList(crawlingData);
    }

    @Override
    public IndicatorsType getIndicatorsType() {
        return IndicatorsType.EXCHANGE_RATE;
    }

    private ExchangeItemInfo getExchangeCrawlingData() throws JsonProcessingException {
        String crawlingResData = restClientUtils.httpCallGetJsonString(exchangeRateIndicatorsUrl);
        log.info("Data : {}", crawlingResData);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(crawlingResData, ExchangeItemInfo.class);
    }

    private List<IndicatorsInfoDto> convertToIndicatorsInfoList(ExchangeItemInfo crawlingData) {
        List<IndicatorsInfoDto> resDataList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate asOfDate = LocalDate.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        Map<String, ExchangeItemInfo.ConversionsInfo> conversionsMap = crawlingData.getConversions();
        ExchangeItemInfo.ConversionsInfo conversionsInfo = conversionsMap.get("USD");
        List<IndicatorsSymbol> symbols = IndicatorsSymbol.getExchangeSymbol();
        for (IndicatorsSymbol symbol : symbols) {
            IndicatorsInfoDto indicatorsInfo = new IndicatorsInfoDto();
            indicatorsInfo.setIndicatorsInfo(
                    IndicatorsInfoDto.IndicatorsInfo.builder()
                            .symbol(symbol.getSymbol())
                            .name(symbol.getName())
                            .country(symbol.getCountry())
                            .indicatorsType(IndicatorsType.EXCHANGE_RATE)
                            .build()
            );
            indicatorsInfo.setIndexInfo(
                    IndicatorsInfoDto.IndexInfo.builder()
                            .netChange(0)
                            .percentChange(0)
                            .price(conversionsInfo.getPriceBySymbol(symbol))
                            .hasPositivePrice(true)
                            .asOfDate(asOfDate)
                            .build()
            );
            resDataList.add(indicatorsInfo);
        }
        return resDataList;
    }
}