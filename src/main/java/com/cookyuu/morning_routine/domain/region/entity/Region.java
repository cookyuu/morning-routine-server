package com.cookyuu.morning_routine.domain.region.entity;

import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_REGION")
public class Region extends BaseTimeEntity {
    @Id
    @Column(name = "region_code")
    private String code;

    @Column(name = "region_full_name", nullable = false)
    private String fullName;

    @Column(name = "region_spot_name", nullable = false)
    private String spotName;

    @OneToMany(mappedBy = "region")
    private List<RegionInterest> regionInterests = new ArrayList<>();
}
