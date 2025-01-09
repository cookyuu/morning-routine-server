package com.cookyuu.morning_routine.domain.weather.controller;

import com.cookyuu.morning_routine.domain.weather.dto.WeatherDetailDto;
import com.cookyuu.morning_routine.domain.weather.facade.WeatherFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherFacade weatherFacade;

    @GetMapping
    public ApiResponse<WeatherDetailDto> getWeatherDetail(@RequestParam(name = "region") String region) throws URISyntaxException {
        WeatherDetailDto collectWeatherInfo = weatherFacade.getWeatherDetail(region);
        return ApiResponse.success(collectWeatherInfo);
    }

    @PostMapping("/collection")
    public ApiResponse<String> collectWeatherDataOfInterestRegions() throws URISyntaxException {
        weatherFacade.collectWeatherDataOfInterestRegions();
        return ApiResponse.success("관심 지역 날씨 정보 수집 완료.");
    }
}
