package com.cookyuu.morning_routine.domain.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherType {
    TMP("온도", "°C"),
    UUU("풍속(동서)","m/s"),
    VVV("풍속(남북)","m/s"),
    VEC("풍향","deg"),
    WSD("풍속","m/s"),
    SKY("하늘상태",""),
    PTY("강수형태",""),
    POP("강수확률","%"),
    WAV("파고","M"),
    PCP("강수량","mm"),
    REH("습도","%"),
    SNO("적설량", "cm"),
    TMX("최고기온","°C"),
    TMN("최저기온","°C")
    ;

    private String name;
    private String unit;
}