package com.cookyuu.morning_routine.domain.stock.controller;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.facade.StockCrawlingFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {

    private final StockCrawlingFacade stockCrawlingFacade;

    @GetMapping("/crawling")
    public ApiResponse<String> crawlingStock(@RequestParam Country country) throws JsonProcessingException {
        stockCrawlingFacade.crawlingStock(country);
        return ApiResponse.success();
    }

}
