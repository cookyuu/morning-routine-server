package com.cookyuu.morning_routine.domain.stock.service;

import com.cookyuu.morning_routine.batch.crawling.stock.data.USStockItemInfo;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.stock.dto.StockInfoDto;
import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.entity.StockIndex;
import com.cookyuu.morning_routine.global.utils.RestClientUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class USStockCrawlingService implements StockCrawlingService{

    @Value("${collector.us-stock.url}")
    private String stockCrawlingBaseUrl;

    private final RestClientUtils restClientUtils;
    private final StockService stockService;
    private final StockIndexService stockIndexService;

    @Override
    public void crawlingStock(Country country) throws JsonProcessingException {
        Iterator<USStockItemInfo.Data.Table.Row> stockInfoIterator;
        String crawlingResData = "";
        List<StockInfoDto> stockInfoList = new ArrayList<>();
        List<Stock> stockList= new ArrayList<>();
        List<StockIndex> stockIndexList = new ArrayList<>();
        crawlingResData = restClientUtils.httpCallGetJsonString(stockCrawlingBaseUrl);
        log.debug("[Crawling] Data : {}", crawlingResData);

        ObjectMapper objectMapper = new ObjectMapper();
        USStockItemInfo resCrawlingData = objectMapper.readValue(crawlingResData, USStockItemInfo.class);
        List<USStockItemInfo.Data.Table.Row> stockCrawlingData = resCrawlingData.getData().getTable().getRows();
        stockInfoIterator = stockCrawlingData.iterator();
        while (stockInfoIterator.hasNext()) {
            USStockItemInfo.Data.Table.Row item = stockInfoIterator.next();
            stockInfoList.add(convertToStockInfo(item));
        }

        for (StockInfoDto stockInfo : stockInfoList) {
            StockInfoDto.CrawlingInfo crlStock = stockInfo.getCrawlingInfo();
            StockInfoDto.CrawlingIndexInfo crlStockIdx = stockInfo.getCrawlingIndexInfo();
            Stock stock = stockService.findBySymbolNullable(crlStock.getSymbol());
            if (stock == null) {
                stock = crlStock.toEntity();
                stockList.add(stock);
            }
            StockIndex stockIndex = crlStockIdx.toEntity(stock);
            stockIndexList.add(stockIndex);
        }

        stockService.saveAll(stockList);
        stockIndexService.saveAll(stockIndexList);
    }

    private StockInfoDto convertToStockInfo(USStockItemInfo.Data.Table.Row stockData) {
        StockInfoDto stockInfo = new StockInfoDto();
        stockInfo.getCrawlingInfo().setCrawlingInfo(stockData.getSymbol(), stockData.getName(), Country.US);
        stockInfo.getCrawlingIndexInfo().setCrawlingInfo(stockData.getMarketCap(), stockData.getLastsale(), stockData.getNetchange(), stockData.getPctchange());
        return stockInfo;
    }

    @Override
    public Country getCountryType() {
        return Country.US;
    }
}
