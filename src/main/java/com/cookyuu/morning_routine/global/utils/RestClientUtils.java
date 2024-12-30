package com.cookyuu.morning_routine.global.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RestClientUtils {
    RestClient restClient = RestClient.create();
    public String httpCallGetJsonString(String url) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }
}
