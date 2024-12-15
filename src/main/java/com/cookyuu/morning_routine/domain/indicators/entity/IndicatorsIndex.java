package com.cookyuu.morning_routine.domain.indicators.entity;

import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_INDICATORS_INDEX")
public class IndicatorsIndex extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indicators_id")
    private Long id;

    @Column(name = "net_change", nullable = false)
    private Long netChange;

    @Column(name = "percent_change", nullable = false)
    private Long percentChange;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "as_of_date", nullable = false)
    private LocalDate asOfDate;

    @ManyToOne
    @JoinColumn(name = "indicators_symbol")
    private Indicators indicators;
}
