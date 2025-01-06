package com.cookyuu.morning_routine.domain.region.entity;

import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_REGION")
public class Region extends BaseTimeEntity {
    @Id
    @Column(name = "region_code")
    private String code;

    @Column(name = "classification")
    private String classification;

    @Column(name = "first_region")
    private String firstRegion;

    @Column(name = "second_region")
    private String secondRegion;

    @Column(name = "third_region")
    private String thirdRegion;

    @Column(name = "grid_x")
    private int gridX;

    @Column(name = "grid_y")
    private int gridY;

    @Column(name = "longitude_hour")
    private int longitudeHour;

    @Column(name = "longitude_min")
    private int longitudeMin;

    @Column(name = "longitude_sec")
    private float longitudeSec;

    @Column(name = "latitude_hour")
    private int latitudeHour;

    @Column(name = "latitude_min")
    private int latitudeMin;

    @Column(name = "latitude_sec")
    private float latitudeSec;

    @OneToMany(mappedBy = "region")
    private List<RegionInterest> regionInterests = new ArrayList<>();
}
