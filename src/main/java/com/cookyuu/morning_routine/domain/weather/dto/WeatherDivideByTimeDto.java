package com.cookyuu.morning_routine.domain.weather.dto;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.weather.entity.Weather;
import com.cookyuu.morning_routine.domain.weather.entity.WeatherType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDivideByTimeDto {
    private String baseDate;
    private Region region;
    private Map<String, Map<String, String>> weatherInfo;

    public List<Weather> toEntityList() {
        List<Weather> weatherList = new LinkedList<>();
        Set<String> baseTimes = weatherInfo.keySet();
        for (String baseTime : baseTimes) {
            Map<String, String> weatherInfoMap = weatherInfo.get(baseTime);
            weatherList.add(Weather.builder()
                    .baseDate(baseDate)
                    .baseTime(baseTime)
                    .region(region)
                    .tmp(weatherInfoMap.get(WeatherType.TMP.name()))
                    .uuu(weatherInfoMap.get(WeatherType.UUU.name()))
                    .vvv(weatherInfoMap.get(WeatherType.VVV.name()))
                    .vec(weatherInfoMap.get(WeatherType.VEC.name()))
                    .wsd(weatherInfoMap.get(WeatherType.WSD.name()))
                    .sky(weatherInfoMap.get(WeatherType.SKY.name()))
                    .pty(weatherInfoMap.get(WeatherType.PTY.name()))
                    .pop(weatherInfoMap.get(WeatherType.POP.name()))
                    .wav(weatherInfoMap.get(WeatherType.WAV.name()))
                    .pcp(weatherInfoMap.get(WeatherType.PCP.name()))
                    .reh(weatherInfoMap.get(WeatherType.REH.name()))
                    .sno(weatherInfoMap.get(WeatherType.SNO.name()))
                    .tmx(weatherInfoMap.get(WeatherType.TMX.name()))
                    .tmn(weatherInfoMap.get(WeatherType.TMN.name()))
                    .build());
        }
        return weatherList;
    }
}
