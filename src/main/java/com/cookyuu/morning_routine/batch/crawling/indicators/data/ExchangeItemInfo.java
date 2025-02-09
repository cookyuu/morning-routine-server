package com.cookyuu.morning_routine.batch.crawling.indicators.data;

import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsSymbol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeItemInfo {
    @JsonProperty("dataAsOf")
    private String dataAsOf;

    @JsonProperty("generatedAt")
    private String generatedAt;

    @JsonProperty("conversions")
    private Map<String, ConversionsInfo> conversions;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConversionsInfo {
        @JsonProperty("USD")
        private float USD;
        @JsonProperty("JPY")
        private float JPY;
        @JsonProperty("BGN")
        private float BGN;
        @JsonProperty("CZK")
        private float CZK;
        @JsonProperty("DKK")
        private float DKK;
        @JsonProperty("GBP")
        private float GBP;
        @JsonProperty("HUF")
        private float HUF;
        @JsonProperty("PLN")
        private float PLN;
        @JsonProperty("RON")
        private float RON;
        @JsonProperty("SEK")
        private float SEK;
        @JsonProperty("CHF")
        private float CHF;
        @JsonProperty("ISK")
        private float ISK;
        @JsonProperty("NOK")
        private float NOK;
        @JsonProperty("TRY")
        private float TRY;
        @JsonProperty("AUD")
        private float AUD;
        @JsonProperty("BRL")
        private float BRL;
        @JsonProperty("CAD")
        private float CAD;
        @JsonProperty("CNY")
        private float CNY;
        @JsonProperty("HKD")
        private float HKD;
        @JsonProperty("IDR")
        private float IDR;
        @JsonProperty("ILS")
        private float ILS;
        @JsonProperty("INR")
        private float INR;
        @JsonProperty("KRW")
        private float KRW;
        @JsonProperty("MXN")
        private float MXN;
        @JsonProperty("MYR")
        private float MYR;
        @JsonProperty("NZD")
        private float NZD;
        @JsonProperty("PHP")
        private float PHP;
        @JsonProperty("SGD")
        private float SGD;
        @JsonProperty("THB")
        private float THB;
        @JsonProperty("ZAR")
        private float ZAR;
        @JsonProperty("EUR")
        private float EUR;

        public float getPriceBySymbol(IndicatorsSymbol symbol) {
            return switch (symbol) {
                case JPY -> JPY;
                case CNY -> CNY;
                case KRW -> KRW;
                case EUR -> EUR;
                default -> 0;
            };
        }
    }
}
