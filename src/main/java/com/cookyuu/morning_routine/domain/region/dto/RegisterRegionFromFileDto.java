package com.cookyuu.morning_routine.domain.region.dto;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import jakarta.persistence.Column;
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
        private String classification; // 0
        private String code; // 1
        private String firstRegion; // 2
        private String secondRegion;    // 3
        private String thirdRegion; // 4
        private int gridX;  // 5
        private int gridY;  // 6
        private int longitudeHour;   // 7
        private int longitudeMin;   // 8
        private float longitudeSec; // 9
        private int latitudeHour;   // 10
        private int latitudeMin; // 11
        private float latitudeSec; // 12

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
