package com.cookyuu.morning_routine.domain.weather.dto;

import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDetailDto {
    private String regionCode;
    private String regionFullName;
    private String baseDate;
    private List<WeatherInfo> weatherInfoList;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeatherInfo {
        private String baseTime;
        private String tmp;
        private String uuu;
        private String vvv;
        private String vec;
        private String wsd;
        private String sky;
        private String pty;
        private String pop;
        private String wav;
        private String pcp;
        private String reh;
        private String sno;
        private String tmx;
        private String tmn;

        public static WeatherInfo toDto(Weather weather) {
            return WeatherInfo.builder()
                    .baseTime(weather.getBaseTime())
                    .tmp(weather.getTmp())
                    .uuu(weather.getUuu())
                    .vvv(weather.getVvv())
                    .vec(weather.getVec())
                    .wsd(weather.getWsd())
                    .sky(weather.getSky())
                    .pty(weather.getPty())
                    .pop(weather.getPop())
                    .wav(weather.getWav())
                    .pcp(weather.getPcp())
                    .reh(weather.getReh())
                    .sno(weather.getSno())
                    .tmx(weather.getTmx())
                    .tmn(weather.getTmn())
                    .build();
        }
    }

}
