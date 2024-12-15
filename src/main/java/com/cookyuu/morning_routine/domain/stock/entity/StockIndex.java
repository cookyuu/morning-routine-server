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
    @Column(name = "stock_id")
    private Long id;

    @Column(name = "last_sale")
    private Long lastSale;

    @Column(name = "market_cap")
    private Long marketCap;

    @Column(name = "net_change")
    private Long netChange;

    @Column(name = "percent_change")
    private Long percentChange;

    @Column(name = "as_of_date")
    private LocalDate asOfDate;

    @ManyToOne
    @JoinColumn(name = "stock_symbol")
    private Stock stock;
}
