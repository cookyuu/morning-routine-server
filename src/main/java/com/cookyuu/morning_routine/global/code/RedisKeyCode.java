package com.cookyuu.morning_routine.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisKeyCode {

    LOGOUT_TOKEN("logout:token:")
    ,REFRESH_TOKEN("refresh:token:");

    String separator;
}
