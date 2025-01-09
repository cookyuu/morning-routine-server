package com.cookyuu.morning_routine.domain.weather.crawler;

import com.cookyuu.morning_routine.batch.crawling.weather.WeatherItemInfo;
import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.weather.dto.WeatherDivideByTimeDto;
import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import com.cookyuu.morning_routine.domain.weather.entity.WeatherApiParamKey;
import com.cookyuu.morning_routine.global.utils.RestClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherCrawler {
    @Value("${collector.weather.url}")
    private String openApiUrl;

    @Value("${collector.weather.service-key}")
    private String serviceKey;

    @Value("${collector.weather.rows-num}")
    private String rowsNum;

    @Value("${collector.weather.type}")
    private String type;

    private final RestClientUtils restClientUtils;

    public List<Weather> collectWeatherData(List<Region> regions) throws URISyntaxException {
        List<WeatherItemInfo> collectDataList = collectFromOpenApi(regions);
        List<WeatherDivideByTimeDto> dividedList = divideCollectDataByTime(collectDataList);
        return convertToEntityList(dividedList);
    }

    public List<Weather> collectWeatherData(Region region) throws URISyntaxException {
        WeatherItemInfo weatherItemInfo = collectFromOpenApi(region);
        WeatherDivideByTimeDto dividedWeather = divideCollectDataByTime(weatherItemInfo);
        return convertToEntityList(dividedWeather);
    }

    private List<WeatherItemInfo> collectFromOpenApi(List<Region> regions) throws URISyntaxException {
        List<WeatherItemInfo> weatherItemInfoList = new ArrayList<>();
        Map<String, String> paramMap = new HashMap<>();
        LocalDate now = LocalDate.now();
        String mm = String.valueOf(now.getMonthValue());
        String dd = String.valueOf(now.getDayOfMonth());
        mm = mm.length() == 1 ? "0".concat(mm) : mm;
        dd = dd.length() == 1 ? "0".concat(dd) : dd;
        String baseDate = String.valueOf(now.getYear()).concat(mm).concat(dd);
//        String baseDate = "20250109";
        String baseTime = "0500";
        String gridX = "";
        String gridY = "";

        for (Region region : regions) {
            gridX = String.valueOf(region.getGridX());
            gridY = String.valueOf(region.getGridY());

            paramMap.put(WeatherApiParamKey.SERVICE_KEY.getKey(), serviceKey);
            paramMap.put(WeatherApiParamKey.DATA_TYPE.getKey(), type);
            paramMap.put(WeatherApiParamKey.BASE_DATE.getKey(), baseDate);
            paramMap.put(WeatherApiParamKey.BASE_TIME.getKey(), baseTime);
            paramMap.put(WeatherApiParamKey.GRID_X.getKey(), gridX);
            paramMap.put(WeatherApiParamKey.GRID_Y.getKey(), gridY);
            paramMap.put(WeatherApiParamKey.NUM_OF_ROWS.getKey(), rowsNum);

            WeatherItemInfo.ResData collectData =  restClientUtils.httpCallGetJsonObject(openApiUrl,paramMap, WeatherItemInfo.ResData.class);
            if (collectData != null) {
                weatherItemInfoList.add(WeatherItemInfo.builder().region(region).data(collectData).build());
            }
        }
        if (weatherItemInfoList.isEmpty()) {
            log.info("[Weather] Collection weather data is empty.");
        }
        log.info("[Weather] Collect weather data complete. total row cnt : {}", weatherItemInfoList.get(0).getData().getResponse().getBody().getTotalCount());
        return weatherItemInfoList;
    }

    private WeatherItemInfo collectFromOpenApi(Region region) throws URISyntaxException {
        WeatherItemInfo weatherItemInfo = new WeatherItemInfo();
        Map<String, String> paramMap = new HashMap<>();
        LocalDate now = LocalDate.now();
        String mm = String.valueOf(now.getMonthValue());
        String dd = String.valueOf(now.getDayOfMonth());
        mm = mm.length() == 1 ? "0".concat(mm) : mm;
        dd = dd.length() == 1 ? "0".concat(dd) : dd;
        String baseDate = String.valueOf(now.getYear()).concat(mm).concat(dd);
        String baseTime = "0500";
        String gridX = "";
        String gridY = "";
        gridX = String.valueOf(region.getGridX());
        gridY = String.valueOf(region.getGridY());

        paramMap.put(WeatherApiParamKey.SERVICE_KEY.getKey(), serviceKey);
        paramMap.put(WeatherApiParamKey.DATA_TYPE.getKey(), type);
        paramMap.put(WeatherApiParamKey.BASE_DATE.getKey(), baseDate);
        paramMap.put(WeatherApiParamKey.BASE_TIME.getKey(), baseTime);
        paramMap.put(WeatherApiParamKey.GRID_X.getKey(), gridX);
        paramMap.put(WeatherApiParamKey.GRID_Y.getKey(), gridY);
        paramMap.put(WeatherApiParamKey.NUM_OF_ROWS.getKey(), rowsNum);

        WeatherItemInfo.ResData collectData =  restClientUtils.httpCallGetJsonObject(openApiUrl,paramMap, WeatherItemInfo.ResData.class);

        if (collectData == null) {
            log.info("[Weather] Collection weather data is empty.");
            return weatherItemInfo;
        }
        weatherItemInfo = WeatherItemInfo.builder().region(region).data(collectData).build();
        log.info("[Weather] Collect weather data complete. total row cnt : {}", weatherItemInfo.getData().getResponse().getBody().getTotalCount());
        return weatherItemInfo;
    }

    private List<WeatherDivideByTimeDto> divideCollectDataByTime(List<WeatherItemInfo> collectDataList) {
        List<WeatherDivideByTimeDto> dividedList = new ArrayList<>();
        for (WeatherItemInfo collectData : collectDataList) {
            Map<String, Map<String, String>> dividedWeatherInfoMap = new HashMap<>();

            List<WeatherItemInfo.ResData.Response.Body.Items.Item> items = collectData.getData().getResponse().getBody().getItems().getItem();
            for (WeatherItemInfo.ResData.Response.Body.Items.Item item  : items) {
                if (dividedWeatherInfoMap.containsKey(item.getFcstTime())) {
                    Map<String, String> weatherInfo = dividedWeatherInfoMap.get(item.getFcstTime());
                    weatherInfo.put(item.getCategory(), item.getFcstValue());
                } else {
                    Map<String, String> weatherInfo = new HashMap<>();
                    weatherInfo.put(item.getCategory(), item.getFcstValue());
                    dividedWeatherInfoMap.put(item.getFcstTime(), weatherInfo);
                }
            }

            dividedList.add(WeatherDivideByTimeDto.builder()
                    .weatherInfo(dividedWeatherInfoMap)
                    .baseDate(items.get(0).getBaseDate())
                    .region(collectData.getRegion())
                    .build());
        }
        return dividedList;
    }
    private WeatherDivideByTimeDto divideCollectDataByTime(WeatherItemInfo collectData) {
        Map<String, Map<String, String>> dividedWeatherInfoMap = new HashMap<>();

        List<WeatherItemInfo.ResData.Response.Body.Items.Item> items = collectData.getData().getResponse().getBody().getItems().getItem();
        for (WeatherItemInfo.ResData.Response.Body.Items.Item item  : items) {
            if (dividedWeatherInfoMap.containsKey(item.getFcstTime())) {
                Map<String, String> weatherInfo = dividedWeatherInfoMap.get(item.getFcstTime());
                weatherInfo.put(item.getCategory(), item.getFcstValue());
            } else {
                Map<String, String> weatherInfo = new HashMap<>();
                weatherInfo.put(item.getCategory(), item.getFcstValue());
                dividedWeatherInfoMap.put(item.getFcstTime(), weatherInfo);
            }
        }
        return WeatherDivideByTimeDto.builder()
                .weatherInfo(dividedWeatherInfoMap)
                .baseDate(items.get(0).getBaseDate())
                .region(collectData.getRegion())
                .build();
    }


    private List<Weather> convertToEntityList(List<WeatherDivideByTimeDto> dataList) {
        List<Weather> entityList = new LinkedList<>();
        for (WeatherDivideByTimeDto data : dataList) {
            List<Weather> weatherList = data.toEntityList();
            entityList.addAll(weatherList);
        }
        return entityList;
    }

    private List<Weather> convertToEntityList(WeatherDivideByTimeDto data) {
        return data.toEntityList();
    }
}