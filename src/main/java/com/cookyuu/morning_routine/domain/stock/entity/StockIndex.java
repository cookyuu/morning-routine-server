package com.cookyuu.morning_routine.domain.stock.entity;

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
@Table(name = "MR_STOCK_INDEX")
public class StockIndex extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_index_id")
    private Long id;

    @Column(name = "market_cap")
    private Long marketCap;

    @Column(name = "ori_closing_price")
    private float oriClosingPrice;

    @Column(name = "ori_change_price")
    private float oriPriceChange;

    @Column(name = "ori_change_percent")
    private float oriPercentChange;

    @Column(name = "ori_has_positive_stock")
    private boolean oriHasPositiveStock;

    @Column(name = "after_load_completed")
    private boolean afterLoadCompleted;

    @Column(name = "after_closing_price")
    private float afterClosingPrice;

    @Column(name = "after_change_price")
    private float afterChangePrice;

    @Column(name = "after_change_percent")
    private float afterChangePercent;

    @Column(name = "after_has_positive_stock")
    private boolean afterHasPositiveStock;

    @Column(name = "price_base_date")
    private LocalDate priceBaseDate;

    @ManyToOne
    @JoinColumn(name = "stock_symbol")
    private Stock stock;

    @Builder
    public StockIndex(Long marketCap, float oriClosingPrice, float oriPriceChange, float oriPercentChange, boolean oriHasPositiveStock, LocalDate priceBaseDate, Stock stock) {
        this.marketCap = marketCap;
        this.oriClosingPrice = oriClosingPrice;
        this.oriPriceChange = oriPriceChange;
        this.oriPercentChange = oriPercentChange;
        this.oriHasPositiveStock = oriHasPositiveStock;
        this.priceBaseDate = priceBaseDate;
        this.stock = stock;
    }
}
