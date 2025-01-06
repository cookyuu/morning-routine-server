package com.cookyuu.morning_routine.domain.weather.dto;

import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherInfo {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
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
}
