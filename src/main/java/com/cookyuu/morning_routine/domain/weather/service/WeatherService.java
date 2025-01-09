package com.cookyuu.morning_routine.domain.weather.service;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.weather.dto.WeatherDetailDto;
import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import com.cookyuu.morning_routine.domain.weather.repository.WeatherRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;

    @Transactional
    public void saveData(List<Weather> weatherInfoList) {
        weatherRepository.saveAll(weatherInfoList);
    }

    @Transactional(readOnly = true)
    public boolean checkCollectedWeatherOfRegion(Region region) {
        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return weatherRepository.existsByRegionAndBaseDate(region, baseDate);
    }

    @Transactional(readOnly = true)
    public WeatherDetailDto getWeatherDetail(Region region) {
        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<Weather> weathers = weatherRepository.findAllByRegionAndBaseDateOrderByBaseTimeAsc(region, baseDate);
        return convertWeatherDetailFromWeatherList(weathers, region);
    }

    private List<WeatherDetailDto.WeatherInfo> convertToWeatherInfoList(List<Weather> weathers) {
        List<WeatherDetailDto.WeatherInfo> weatherInfoList = new ArrayList<>();
        for (Weather weather : weathers) {
            weatherInfoList.add(WeatherDetailDto.WeatherInfo.toDto(weather));
        }
        return weatherInfoList;
    }

    public WeatherDetailDto convertWeatherDetailFromWeatherList(List<Weather> weathers, Region region) {
        weathers.sort(Comparator.comparing(Weather::getBaseTime));
        StringBuilder sb = new StringBuilder();
        sb.append(region.getFirstRegion());
        String secondRegion = region.getSecondRegion();
        String thirdRegion = region.getThirdRegion();
        if (!StringUtils.isBlank(secondRegion)) {
            sb.append(" ").append(region.getSecondRegion());
        }
        if (!StringUtils.isBlank(thirdRegion)) {
            sb.append(" ").append(region.getThirdRegion());
        }
        String regionFullName = sb.toString();
        List<WeatherDetailDto.WeatherInfo> weatherInfoList = convertToWeatherInfoList(weathers);
        return WeatherDetailDto.builder()
                .regionCode(region.getCode())
                .regionFullName(regionFullName)
                .weatherInfoList(weatherInfoList)
                .build();
    }
}
