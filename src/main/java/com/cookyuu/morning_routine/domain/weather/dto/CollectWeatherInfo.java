package com.cookyuu.morning_routine.domain.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CollectWeatherInfo {
    private List<Data> datas;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private String value;
        private String weatherType;
        private String typeName;
        private LocalDateTime forecastDateTime;
        private String regionFullName;
    }

}
