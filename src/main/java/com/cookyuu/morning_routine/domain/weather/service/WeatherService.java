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
import java.time.LocalDateTime;
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
        LocalDateTime now = LocalDateTime.now();
        String baseDate = "";
        if (now.getHour() < 5) {
            baseDate = now.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } else {
            baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        return weatherRepository.existsByRegionAndBaseDate(region, baseDate);
    }

    @Transactional(readOnly = true)
    public WeatherDetailDto getWeatherDetail(Region region) {
        LocalDateTime now = LocalDateTime.now();
        String baseDate = "";
        if (now.getHour() < 5) {
            baseDate = now.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } else {
            baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
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
        if (!StringUtils.isBlank(secondRegion)) {
            sb.append(" ").append(region.getSecondRegion());
        }
        String regionFullName = sb.toString();
        List<WeatherDetailDto.WeatherInfo> weatherInfoList = convertToWeatherInfoList(weathers);
        return WeatherDetailDto.builder()
                .regionCode(region.getCode())
                .regionFullName(regionFullName)
                .baseDate(weathers.get(0).getBaseDate())
                .weatherInfoList(weatherInfoList)
                .build();
    }
}
