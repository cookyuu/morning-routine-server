package com.cookyuu.morning_routine.domain.indicators.crawler;

import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.domain.indicators.dto.crawler.ExchangeRateInfo;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BondCrawler implements IndicatorsCrawler {
    @Override
    public List<IndicatorsInfoDto> crawlingIndicators() throws JsonProcessingException {
        getIndicatorsInfoList();
        return null;
    }

    private void getIndicatorsInfoList() {
        final String crawlingUrl = "https://www.investing.com/";
        log.info("[Indicators] crawling url : {}", crawlingUrl);
        Connection conn = Jsoup.connect(crawlingUrl);

        try {
            Document document = conn.get();
            List<ExchangeRateInfo> tbody = getIndicatorsList(document);   // 데이터 리스트
        } catch (IOException ignored) {
        }
    }

    private String getIndicatorsHeader(Document document) {
        Elements stockTableBody = document.select("table.type_2 thead tr");
        StringBuilder sb = new StringBuilder();
        for (Element element : stockTableBody) {
            for (Element td : element.select("th")) {
                sb.append(td.text());
                sb.append("   ");
            }
            break;
        }
        return sb.toString();
    }

    private List<ExchangeRateInfo> getIndicatorsList(Document document) {
        List<ExchangeRateInfo> crawlingDataList = new ArrayList<>();
        log.info("[Crawling] data : {}", document.toString());
        Elements exchangeTable = document.select(".data_lst");
        List<Element> rows = exchangeTable.select("tr");
        for (Element row : rows) {
            ExchangeRateInfo exchangeRateInfo = new ExchangeRateInfo();
            List<String> dataLine = new ArrayList<>();
            log.info("row : {}", row.toString());

            String name = row.select(".h_lst").text();
            String price = row.select(".value").text();
            String priceCurrency = row.select(".blind").get(1).text();
            String chagnePrice = row.select(".change").text().trim();
            String changeUpDown = row.select(".blind").last().text();

            log.info("name : {}, price : {}, changePrice : {}", name, price, chagnePrice);
            log.info("통화 단위 : {}, 상승 하락 : {}",priceCurrency, changeUpDown);
        }

        return crawlingDataList;
    }

    @Override
    public IndicatorsType getIndicatorsType() {
        return IndicatorsType.BOND;
    }
}
