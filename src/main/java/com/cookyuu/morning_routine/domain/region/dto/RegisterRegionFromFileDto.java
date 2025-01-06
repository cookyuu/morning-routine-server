package com.cookyuu.morning_routine.domain.region.dto;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RegisterRegionFromFileDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String filepath;
    }


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileData {
        private String classification;
        private String code;
        private String firstRegion;
        private String secondRegion;
        private String thirdRegion;
        private int gridX;
        private int gridY;
        private int longitudeHour;
        private int longitudeMin;
        private float longitudeSec;
        private int latitudeHour;
        private int latitudeMin;
        private float latitudeSec;

        public Region toEntity() {
            return Region.builder()
                    .classification(classification)
                    .code(code)
                    .firstRegion(firstRegion)
                    .secondRegion(secondRegion)
                    .thirdRegion(thirdRegion)
                    .gridX(gridX)
                    .gridY(gridY)
                    .longitudeHour(longitudeHour)
                    .latitudeMin(latitudeMin)
                    .latitudeSec(longitudeSec)
                    .latitudeHour(latitudeHour)
                    .latitudeMin(latitudeMin)
                    .latitudeSec(latitudeSec)
                    .build();
        }
    }
}
