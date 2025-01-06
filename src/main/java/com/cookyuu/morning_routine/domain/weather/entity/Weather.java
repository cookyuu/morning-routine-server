package com.cookyuu.morning_routine.domain.weather.entity;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_WEATHER")
public class Weather extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Long id;

    @Column(name = "weather_value")
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "weather_type")
    private WeatherType type;

    @Column(name = "forecast_date_time")
    private LocalDateTime forecastDateTime;

    @ManyToOne
    @JoinColumn(name = "region_code")
    private Region region;

    @Builder
    public Weather(String value, WeatherType type, LocalDateTime forecastDateTime, Region region) {
        this.value = value;
        this.type = type;
        this.forecastDateTime = forecastDateTime;
        this.region = region;
    }
}