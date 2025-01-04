package com.cookyuu.morning_routine.domain.indicators.crawler;

import com.cookyuu.morning_routine.batch.crawling.indicators.CoinItemInfo;
import com.cookyuu.morning_routine.batch.crawling.indicators.MaterialItemInfo;
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
public class MaterialCrawler implements IndicatorsCrawler {
    @Value("${collector.material.url}")
    private String materialCrawlingUrl;
    @Override
    public List<IndicatorsInfoDto> crawlingIndicators() throws JsonProcessingException {
        List<MaterialItemInfo> coinItemInfoList = getIndicatorsInfoList();
        return convertToIndicatorsInfoList(coinItemInfoList);
    }

    @Override
    public IndicatorsType getIndicatorsType() {
        return IndicatorsType.RAW_MATERIAL;
    }

    private List<MaterialItemInfo> getIndicatorsInfoList() {
        log.info("[Indicators] crawling url : {}", materialCrawlingUrl);
        List<MaterialItemInfo> crawlingDataList = new ArrayList<>();
        Connection conn = Jsoup.connect(materialCrawlingUrl);

        try {
            Document document = conn.get();
            crawlingDataList = getIndicatorsList(document);   // 데이터 리스트
        } catch (IOException e) {
            log.error("[Crawling::Coin] Exception : ", e);
        }
        return crawlingDataList;
    }

    private List<IndicatorsInfoDto> convertToIndicatorsInfoList(List<MaterialItemInfo> materialItemInfoList) {
        List<IndicatorsInfoDto> resList = new ArrayList<>();
        for (MaterialItemInfo info : materialItemInfoList) {
            IndicatorsInfoDto indicatorsInfo = new IndicatorsInfoDto();
            indicatorsInfo.setIndicatorsInfo(IndicatorsInfoDto.IndicatorsInfo.builder()
                    .symbol(info.getSymbol().getSymbol())
                    .name(info.getSymbol().getName())
                    .country(info.getSymbol().getCountry())
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

    private List<MaterialItemInfo> getIndicatorsList(Document document) {
        List<MaterialItemInfo> crawlingDataList = new ArrayList<>();
        Elements exchangeTable = document.select(".datatable-v2_table__93S4Y");
        List<Element> rows = exchangeTable.select(".datatable-v2_body__8TXQk tr");

        LocalDate now = LocalDate.now();
        LocalDate asOfDate = LocalDate.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        for (Element row : rows) {
            String name = "";
            String symbol = "";
            double price;
            double changePct;
            double changePrice;
            boolean hasPositivePrice;
            List<Element> dataRows = row.select(".datatable-v2_cell__IwP1U");
            IndicatorsSymbol indicatorsSymbol = IndicatorsSymbol.getSymbolByName(dataRows.get(1).text());
            if (indicatorsSymbol == null) continue;
            try {
                price = Double.parseDouble(dataRows.get(3).text().replace(",", ""));
                changePct = Double.parseDouble(dataRows.get(7).text().replace("-", "").replace("+", "").replace("%", ""));
                changePrice = Double.parseDouble(dataRows.get(6).text().replace("-", "").replace("+", ""));
                hasPositivePrice = dataRows.get(6).text().contains("+");
            } catch (NumberFormatException e) {
                log.error("[Crawling::Material] Material Indicators Crawling fail. Exception : ", e);
                throw new MRCrawlingException(ResultCode.CRAWLING_IS_FAIL, "크롤링 데이터가 기존과 일치하지 않습니다.");
            }
            crawlingDataList.add(MaterialItemInfo.builder()
                            .symbol(indicatorsSymbol)
                            .price(price)
                            .changePrice(changePrice)
                            .changePct(changePct)
                            .hasPositivePrice(hasPositivePrice)
                            .asOfDate(asOfDate)
                    .build());
        }
        return crawlingDataList;
    }
}
