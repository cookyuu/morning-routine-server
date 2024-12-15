package com.cookyuu.morning_routine.domain.indicators.entity;

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
@Table(name = "MR_INDICATORS")
public class Indicators extends BaseTimeEntity {
    @Id
    @Column(name = "indicators_symbol")
    private String symbol;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "indicators_type", nullable = false)
    private IndicatorsType indicatorsType;

    @OneToMany(mappedBy = "indicators")
    private List<IndicatorsIndex> indicatorsIndices = new ArrayList<>();
}
