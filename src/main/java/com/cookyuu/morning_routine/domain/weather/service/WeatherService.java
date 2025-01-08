package com.cookyuu.morning_routine.domain.weather.service;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.weather.dto.WeatherDetailDto;
import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import com.cookyuu.morning_routine.domain.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public WeatherDetailDto getWeatherDetail(Region region) {
        String regionFullName = region.getFirstRegion().concat(" ").concat(region.getSecondRegion()).concat(" ").concat(region.getThirdRegion());
//        List<Weather> weatherList = weatherRepository.findAllByRegionAnd
        return WeatherDetailDto.builder()
                .regionCode(region.getCode())
                .regionFullName(regionFullName)
//                .weatherInfoList()
                .build();
    }
}
