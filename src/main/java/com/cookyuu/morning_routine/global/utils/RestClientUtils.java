package com.cookyuu.morning_routine.global.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class RestClientUtils {
    RestClient restClient = RestClient.create();
    public String httpCallGetJsonString(String url) {
        log.info("Http Call Url : {}", url);
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }
}
