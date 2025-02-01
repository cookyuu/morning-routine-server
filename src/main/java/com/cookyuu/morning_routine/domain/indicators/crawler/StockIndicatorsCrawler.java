package com.cookyuu.morning_routine.domain.indicators.crawler;

import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.batch.crawling.indicators.data.StockItemInfo;
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
public class StockIndicatorsCrawler implements IndicatorsCrawler {
    @Value("${collector.stock-indicators.url}")
    private String stockIndicatorsUrl;

    @Override
    public List<IndicatorsInfoDto> crawlingIndicators() throws JsonProcessingException {
        List<StockItemInfo> crawlingStockIndicatorsList = getIndicatorsInfoList();
        return convertToIndicatorsInfoList(crawlingStockIndicatorsList);
    }

    @Override
    public IndicatorsType getIndicatorsType() {
        return IndicatorsType.STOCK;
    }

    private List<StockItemInfo> getIndicatorsInfoList() {
        List<StockItemInfo> crawlingDataList = new ArrayList<>();
        log.info("[Indicators] crawling url : {}", stockIndicatorsUrl);
        Connection conn = Jsoup.connect(stockIndicatorsUrl);

        try {
            Document document = conn.get();
            crawlingDataList = getIndicatorsList(document);

        } catch (IOException e) {
            log.error("[Crawling] Exception : ", e);
        }
        return crawlingDataList;
    }

    private List<StockItemInfo> getIndicatorsList(Document document) {
        List<StockItemInfo> crawlingDataList = new ArrayList<>();
        Elements indicatorsData = document.select(".datatable-v2_table__93S4Y");
        List<Element> rows = indicatorsData.select(".datatable-v2_body__8TXQk tr");
        for (Element row : rows) {
            String name = "";
            float endPrice;
            float highestPrice;
            float lowestPrice;
            float changePrice;
            float changePct;
            boolean hasPositivePrice;
            String asOfDate = "";

            List<Element> dataRows = row.select(".datatable-v2_cell__IwP1U");
            if (dataRows.size() != 8) {
                throw new MRCrawlingException(ResultCode.CRAWLING_IS_FAIL, "크롤링 데이터가 기존과 일치하지 않습니다.");
            }
            try {
                name = dataRows.get(1).text();
                endPrice = Float.parseFloat(dataRows.get(2).text().replace(",",""));
                highestPrice = Float.parseFloat(dataRows.get(3).text().replace(",",""));
                lowestPrice = Float.parseFloat(dataRows.get(4).text().replace(",",""));
                changePrice = Float.parseFloat(dataRows.get(5).text().replace("-", "").replace("+", "").replace(",",""));
                changePct = Float.parseFloat(dataRows.get(6).text().replace("-", "").replace("+", "").replace("%", "").replace(",",""));
                hasPositivePrice = dataRows.get(5).text().contains("+");
                asOfDate = dataRows.get(7).text();
            } catch (NumberFormatException e) {
                log.error("[Crawling] Stock Indicators Crawling fail. Exception : ", e);
                throw new MRCrawlingException(ResultCode.CRAWLING_IS_FAIL, "크롤링 데이터가 기존과 일치하지 않습니다.");
            }

            StockItemInfo stockItemInfo = StockItemInfo.builder()
                    .name(name)
                    .endPrice(endPrice)
                    .highestPrice(highestPrice)
                    .lowestPrice(lowestPrice)
                    .changePrice(changePrice)
                    .changePct(changePct)
                    .hasPositivePrice(hasPositivePrice)
                    .asOfDate(asOfDate)
                    .build();
            log.info("name : {}, endPrice : {}, changePrcie : {}, changePct : {}, hasPositive :{}, asOfDate : {}", name, endPrice, changePrice, changePct, hasPositivePrice, asOfDate);
            crawlingDataList.add(stockItemInfo);
        }
        return crawlingDataList;
    }

    private List<IndicatorsInfoDto> convertToIndicatorsInfoList(List<StockItemInfo> stockItemInfoList) {
        log.info("[Crawling::Stock] convert res data list.");
        List<IndicatorsInfoDto> resList = new ArrayList<>();
        for (StockItemInfo info : stockItemInfoList) {
            IndicatorsSymbol symbol = IndicatorsSymbol.getSymbolByName(info.getName());
            if (symbol == null) continue;
            IndicatorsInfoDto indicatorsInfo = new IndicatorsInfoDto();
            indicatorsInfo.setIndicatorsInfo(IndicatorsInfoDto.IndicatorsInfo.builder()
                            .symbol(symbol.getSymbol())
                            .name(symbol.getName())
                            .country(symbol.getCountry())
                            .indicatorsType(IndicatorsType.STOCK)
                    .build());
            indicatorsInfo.setIndexInfo(IndicatorsInfoDto.IndexInfo.builder()
                            .netChange(info.getChangePrice())
                            .percentChange(info.getChangePct())
                            .price(info.getEndPrice())
                            .hasPositivePrice(info.isHasPositivePrice())
                            .asOfDate(LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()))
                    .build());
            resList.add(indicatorsInfo);
            log.info("[Crawling::Stock] convert. name : {}", symbol.getName());
        }
        return resList;
    }
}
