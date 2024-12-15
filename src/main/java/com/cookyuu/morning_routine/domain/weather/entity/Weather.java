package com.cookyuu.morning_routine.domain.weather.entity;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "current_temperature")
    private Long currentTemperature;

    @Column(name = "micro_dust_status")
    private String microDustStatus;

    @Column(name = "today_min_temperature")
    private Long todatyMinTeperature;

    @Column(name = "today_max_temperature")
    private Long todatyMaxTeperature;

    @Column(name = "is_today_rain")
    private boolean isTodayRain;

    @Column(name = "is_today_snow")
    private boolean isTodaySnow;

    @ManyToOne
    @JoinColumn(name = "region_code")
    private Region region;
}
