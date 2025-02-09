package com.cookyuu.morning_routine.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CookieCode {
    REFRESH_TOKEN("refresh_token");
    String key;
}
