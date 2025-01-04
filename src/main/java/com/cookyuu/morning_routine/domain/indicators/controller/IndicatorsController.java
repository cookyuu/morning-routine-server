package com.cookyuu.morning_routine.domain.indicators.controller;

import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.facade.IndicatorsCrawlingFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/indicators")
public class IndicatorsController {

    private final IndicatorsCrawlingFacade indicatorsCrawlingFacade;

    @GetMapping("/crawling")
    public ApiResponse<String> crawlingIndicators(@RequestParam IndicatorsType type) throws JsonProcessingException {
        indicatorsCrawlingFacade.crawlingStock(type);
        return ApiResponse.success();
    }
}
