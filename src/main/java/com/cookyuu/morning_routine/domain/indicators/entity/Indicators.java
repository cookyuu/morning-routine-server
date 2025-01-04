package com.cookyuu.morning_routine.domain.indicators.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_INDICATORS")
public class Indicators extends BaseTimeEntity {
    @Id
    @Column(name = "indicators_symbol")
    private String symbol;

    @Column(name = "indicators_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "indicators_type", nullable = false)
    private IndicatorsType indicatorsType;

    @Enumerated(EnumType.STRING)
    @Column(name = "indicators_country", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "indicators")
    private List<IndicatorsIndex> indicatorsIndices = new ArrayList<>();

    @Builder
    public Indicators(String symbol, String name, IndicatorsType indicatorsType, Country country) {
        this.symbol = symbol;
        this.name = name;
        this.indicatorsType = indicatorsType;
        this.country = country;
    }
}
