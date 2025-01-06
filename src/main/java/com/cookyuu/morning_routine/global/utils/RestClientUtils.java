package com.cookyuu.morning_routine.global.utils;

import com.cookyuu.morning_routine.domain.weather.entity.WeatherApiParamKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
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

    public String httpCallGetJsonString (String url, Map<String, String> param) throws URISyntaxException {
        Set<String> paramKeySet = param.keySet();
        Iterator<String> iteratorParam = paramKeySet.iterator();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
        while (iteratorParam.hasNext()) {
            String paramKey = iteratorParam.next();
            if (paramKey.equals(WeatherApiParamKey.SERVICE_KEY.getKey())) {
                continue;
            }
            uriBuilder.queryParam(paramKey, param.get(paramKey));
        }

        String callUri = uriBuilder.toUriString();
        if (paramKeySet.contains(WeatherApiParamKey.SERVICE_KEY.getKey())) {
            callUri = callUri.concat("&").concat(WeatherApiParamKey.SERVICE_KEY.getKey()).concat("=").concat(param.get(WeatherApiParamKey.SERVICE_KEY.getKey()));
        }
        log.debug("Http Call Uri : {}", callUri);
        URI uri = new URI(callUri);
        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(String.class);
    }

    public <T> T httpCallGetJsonObject (String url, Map<String, String> param, Class<T> resClass) throws URISyntaxException {
        Set<String> paramKeySet = param.keySet();
        Iterator<String> iteratorParam = paramKeySet.iterator();
        UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
        while (iteratorParam.hasNext()) {
            String paramKey = iteratorParam.next();
            if (paramKey.equals(WeatherApiParamKey.SERVICE_KEY.getKey())) {
                continue;
            }
            uriBuilder.queryParam(paramKey, param.get(paramKey));
        }
        String callUri = uriBuilder.toUriString();
        if (paramKeySet.contains(WeatherApiParamKey.SERVICE_KEY.getKey())) {
            callUri = callUri.concat("&").concat(WeatherApiParamKey.SERVICE_KEY.getKey()).concat("=").concat(param.get(WeatherApiParamKey.SERVICE_KEY.getKey()));
        }

        log.info("Http Call Uri : {}", callUri);
        URI uri = new URI(callUri);
        return restClient
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .retrieve()
                .body(resClass);
    }
}
