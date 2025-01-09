package com.cookyuu.morning_routine.domain.weather.repository;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    boolean existsByRegionAndBaseDate(Region region, String baseDate);

    List<Weather> findAllByRegionAndBaseDateOrderByBaseTimeAsc(Region region, String baseDate);
}
