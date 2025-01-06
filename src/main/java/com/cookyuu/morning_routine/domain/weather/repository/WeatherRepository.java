package com.cookyuu.morning_routine.domain.weather.repository;

import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
