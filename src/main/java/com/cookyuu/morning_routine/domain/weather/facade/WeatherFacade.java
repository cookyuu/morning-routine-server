package com.cookyuu.morning_routine.domain.weather.facade;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.region.service.RegionService;
import com.cookyuu.morning_routine.domain.weather.crawler.WeatherCrawler;
import com.cookyuu.morning_routine.domain.weather.dto.CollectWeatherInfo;
import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import com.cookyuu.morning_routine.domain.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherFacade {
    private final WeatherCrawler weatherCrawler;
    private final WeatherService weatherService;
    private final RegionService regionService;

    public void collectWeatherDataOfInterestRegions() throws URISyntaxException {
        List<Region> interestRegions = regionService.getInterestRegions();
        if (interestRegions.isEmpty()) {
            log.info("[Weather] Interest region is empty.");
            return ;
        }
        List<Weather> weatherInfoList = weatherCrawler.collectWeatherData(interestRegions);
        weatherService.saveData(weatherInfoList);
        log.info("[Weather] Collect weather data of interest regions. complete, region cnt : {}, row cnt : {}", interestRegions.size(), weatherInfoList.size());
    }

    public CollectWeatherInfo getWeatherDetail(String region) {
        // 지역 분리 후 region 테이블에서 찾기
        //
        return null;
    }
}
