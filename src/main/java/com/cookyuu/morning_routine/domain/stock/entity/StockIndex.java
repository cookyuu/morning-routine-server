package com.cookyuu.morning_routine.domain.stock.entity;

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
@Table(name = "MR_STOCK_INDEX")
public class StockIndex extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_index_id")
    private Long id;

    @Column(name = "market_cap")
    private Long marketCap;

    @Column(name = "ori_closing_price")
    private Long oriClosingPrice;

    @Column(name = "ori_change_price")
    private Long oriPriceChange;

    @Column(name = "ori_change_percent")
    private Long oriPercentChange;

    @Column(name = "after_load_completed")
    private boolean afterLoadCompleted;

    @Column(name = "after_closing_price")
    private Long afterClosingPrice;

    @Column(name = "after_change_percent")
    private Long afterChangePercent;

    @Column(name = "after_change_price")
    private Long afterChangePrice;

    @Column(name = "price_base_date")
    private LocalDate priceBaseDate;

    @ManyToOne
    @JoinColumn(name = "stock_symbol")
    private Stock stock;
}
