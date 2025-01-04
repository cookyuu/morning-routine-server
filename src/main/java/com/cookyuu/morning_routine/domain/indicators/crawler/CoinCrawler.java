package com.cookyuu.morning_routine.domain.indicators.crawler;

import com.cookyuu.morning_routine.batch.crawling.indicators.CoinItemInfo;
import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsSymbol;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRCrawlingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CoinCrawler implements IndicatorsCrawler {
    @Value("${collector.coin.url}")
    private String coinCrawlingUrl;

    @Override
    public List<IndicatorsInfoDto> crawlingIndicators() throws JsonProcessingException {
        List<CoinItemInfo>  coinItemInfoList = getIndicatorsInfoList();
        return convertToIndicatorsInfoList(coinItemInfoList);
    }

    private List<CoinItemInfo> getIndicatorsInfoList() {
        log.info("[Indicators] crawling url : {}", coinCrawlingUrl);
        List<CoinItemInfo> crawlingDataList = new ArrayList<>();
        Connection conn = Jsoup.connect(coinCrawlingUrl);

        try {
            Document document = conn.get();
            crawlingDataList = getIndicatorsList(document);   // 데이터 리스트
        } catch (IOException e) {
            log.error("[Crawling::Coin] Exception : ", e);
        }
        return crawlingDataList;
    }

    private List<IndicatorsInfoDto> convertToIndicatorsInfoList(List<CoinItemInfo> coinItemInfoList) {
        List<IndicatorsInfoDto> resList = new ArrayList<>();
        List<IndicatorsSymbol> symbols = IndicatorsSymbol.getCoinSymbol();
        for (CoinItemInfo info : coinItemInfoList) {
            IndicatorsSymbol symbol;
            try {
                symbol = IndicatorsSymbol.valueOf(info.getSymbol());
            } catch (IllegalArgumentException e) {
                continue;
            }
            IndicatorsInfoDto indicatorsInfo = new IndicatorsInfoDto();
            indicatorsInfo.setIndicatorsInfo(IndicatorsInfoDto.IndicatorsInfo.builder()
                    .symbol(symbol.getSymbol())
                    .name(symbol.getName())
                    .country(symbol.getCountry())
                    .indicatorsType(IndicatorsType.COIN)
                    .build());
            indicatorsInfo.setIndexInfo(IndicatorsInfoDto.IndexInfo.builder()
                    .netChange(info.getChangePrice())
                    .percentChange(info.getChangePct())
                    .price(info.getPrice())
                    .hasPositivePrice(info.isHasPositivePrice())
                    .asOfDate(info.getAsOfDate())
                    .build());
            resList.add(indicatorsInfo);
        }
        return resList;
    }

    private List<CoinItemInfo> getIndicatorsList(Document document) {
        List<CoinItemInfo> crawlingDataList = new ArrayList<>();
        Elements exchangeTable = document.select(".datatable-v2_table__93S4Y");
        List<Element> rows = exchangeTable.select(".datatable-v2_body__8TXQk tr");
        for (Element row : rows) {
            String name = "";
            String symbol = "";
            double price;
            double changePct;
            double changePrice;
            boolean hasPositivePrice;
            LocalDate now = LocalDate.now();
            LocalDate asOfDate = LocalDate.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
            List<Element> dataRows = row.select(".datatable-v2_cell__IwP1U");
            if (dataRows.size() != 9) {
                continue;
            }
            try {
                name = dataRows.get(1).text();
                symbol = dataRows.get(2).text();
                price = Double.parseDouble(dataRows.get(3).text().replace(",",""));
                changePct = Double.parseDouble(dataRows.get(6).text().replace(".","").replace("%","").replace("+", "").replace("-",""));
                changePrice = price * changePct;
                hasPositivePrice = dataRows.get(6).text().contains("+");
            } catch (NumberFormatException e) {
                log.error("[Crawling::Coin] Exception : ", e);
                throw new MRCrawlingException(ResultCode.CRAWLING_IS_FAIL, "크롤링 데이터가 기존과 일치하지 않습니다.");
            }
            crawlingDataList.add(CoinItemInfo.builder()
                    .name(name)
                    .symbol(symbol)
                    .price(price)
                    .changePct(changePct)
                    .changePrice(changePrice)
                    .hasPositivePrice(hasPositivePrice)
                    .asOfDate(asOfDate)
                    .build());
        }
        return crawlingDataList;
    }

    @Override
    public IndicatorsType getIndicatorsType() {
        return IndicatorsType.COIN;
    }
}
