package com.cookyuu.morning_routine.domain.stock.entity;

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
@Table(name = "MR_STOCK")
public class Stock extends BaseTimeEntity {

    @Id
    @Column(name = "stock_symbol")
    private String symbol;

    @Column(name = "stock_name")
    private String name;

    @Column(name = "stock_country")
    private Country country;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockIndex> stockIndices = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockWish> stockWishes = new ArrayList<>();

    @Builder
    public Stock(String symbol, String name, Country country) {
        this.symbol = symbol;
        this.name = name;
        this.country = country;
    }
}
