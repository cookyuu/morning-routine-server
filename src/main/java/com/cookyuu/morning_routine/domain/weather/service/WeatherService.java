package com.cookyuu.morning_routine.domain.weather.service;

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
}
