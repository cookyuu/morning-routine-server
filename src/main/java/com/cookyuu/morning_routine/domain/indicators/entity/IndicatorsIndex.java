package com.cookyuu.morning_routine.domain.indicators.entity;

import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private double netChange;

    @Column(name = "percent_change", nullable = false)
    private double percentChange;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "has_positive_price", nullable = false)
    private boolean hasPositivePrice;

    @Column(name = "as_of_date", nullable = false)
    private LocalDate asOfDate;

    @ManyToOne
    @JoinColumn(name = "indicators_symbol")
    private Indicators indicators;

    @Builder
    public IndicatorsIndex(double netChange, double percentChange, double price, boolean hasPositivePrice, LocalDate asOfDate, Indicators indicators) {
        this.netChange = netChange;
        this.percentChange = percentChange;
        this.price = price;
        this.hasPositivePrice = hasPositivePrice;
        this.asOfDate = asOfDate;
        this.indicators = indicators;
    }
}
