package com.cookyuu.morning_routine.domain.weather.facade;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.region.service.RegionService;
import com.cookyuu.morning_routine.domain.weather.crawler.WeatherCrawler;
import com.cookyuu.morning_routine.domain.weather.dto.WeatherDetailDto;
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

    public WeatherDetailDto getWeatherDetail(int x, int y) throws URISyntaxException {
        WeatherDetailDto weatherDetailInfo;
        Region region = regionService.getRegionForWeatherDetail(x, y);
        log.debug("[Weather::Detail] Region code : {}", region.getCode());
        boolean isCollectedWeatherOfRegion = weatherService.checkCollectedWeatherOfRegion(region);
        log.debug("[Weather::Detail] Is Exists Weather of region : {}", isCollectedWeatherOfRegion);
        if (isCollectedWeatherOfRegion) {
            weatherDetailInfo = weatherService.getWeatherDetail(region);
        } else {
            List<Weather> weathers = weatherCrawler.collectWeatherData(region);
            weatherService.saveData(weathers);
            weatherDetailInfo = weatherService.convertWeatherDetailFromWeatherList(weathers, region);
            log.info("[Weather] Collect weather data of interest regions. complete, row cnt : {}", weathers.size());
        }
        return weatherDetailInfo;
    }
}
