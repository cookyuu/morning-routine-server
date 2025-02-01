package com.cookyuu.morning_routine.batch.crawling.stock.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class USStockItemInfo {
    @JsonProperty("data")
    private Data data;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        @JsonProperty("table")
        private Table table;

        @JsonProperty("totalrecords")
        private int totalRecords;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Table {
            @JsonProperty("rows")
            private List<Row> rows;

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Row {
                @JsonProperty("symbol")
                private String symbol;
                @JsonProperty("name")
                private String name;
                @JsonProperty("lastsale")
                private String lastsale;
                @JsonProperty("netchange")
                private String netchange;
                @JsonProperty("pctchange")
                private String pctchange;
                @JsonProperty("marketCap")
                private String marketCap;
            }
        }


    }
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        @JsonProperty("rCode")
        private int rCode;
    }
}
