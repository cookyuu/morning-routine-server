package com.cookyuu.morning_routine.batch.crawling.weather;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherItemInfo {
    private Region region;
    private ResData data;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResData {
        private Response response;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Response {
            private Header header;
            private Body body;

            @Getter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Header {
                private String resultCode;
                private String resultMsg;
            }

            @Getter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Body {
                private String dataType;
                private Items items;
                private int pageNo;
                private int numOfRows;
                private int totalCount;

                @Getter
                @AllArgsConstructor
                @NoArgsConstructor
                public static class Items {
                    private List<Item> item;

                    @Getter
                    @AllArgsConstructor
                    @NoArgsConstructor
                    public static class Item {
                        private String baseDate;
                        private String baseTime;
                        private String category; // WeatherType
                        private String fcstDate;
                        private String fcstTime;
                        private String fcstValue;
                        private int nx;
                        private int ny;
                    }
                }
            }
        }
    }
}
