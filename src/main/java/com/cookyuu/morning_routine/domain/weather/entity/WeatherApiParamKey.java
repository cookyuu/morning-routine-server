package com.cookyuu.morning_routine.domain.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherApiParamKey {
    SERVICE_KEY("serviceKey"),
    DATA_TYPE("dataType"),
    BASE_DATE("base_date"),
    BASE_TIME("base_time"),
    GRID_X("nx"),
    GRID_Y("ny"),
    NUM_OF_ROWS("numOfRows");

    private String key;
}
