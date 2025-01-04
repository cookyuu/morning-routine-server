package com.cookyuu.morning_routine.batch.stock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
public class USStockItemReader implements ItemReader<CrawlingStockInfo> {
    @Value("${url.crawling.us-stock}")
    private String stockCrawlingBaseUrl;

    private final RestClient restClient;
    private Iterator<CrawlingStockInfo> stockInfoIterator;

    public USStockItemReader() {
        this.restClient = RestClient.builder()
                .baseUrl(stockCrawlingBaseUrl)
                .build();
    }

    @Override
    public CrawlingStockInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (stockInfoIterator == null) {
            getData();
        }
        return stockInfoIterator.hasNext() ? stockInfoIterator.next() : null;
    }

    private void getData() throws JsonProcessingException {
        String jsonResponse = restClient.get()
                .retrieve()
                .body(String.class);
        log.info("Data : {}", jsonResponse);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode rows = root.path("data").path("rows");

        List<CrawlingStockInfo> stocks = objectMapper.convertValue(rows, new TypeReference<List<CrawlingStockInfo>>() {});
        this.stockInfoIterator = stocks.iterator(); // Prepare an iterator for chunk processing
    }
}
