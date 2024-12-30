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

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private Country country;

    @OneToMany(mappedBy = "stock")
    private List<StockIndex> stockIndices = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    private List<StockInterest> stockInterests = new ArrayList<>();

    @Builder
    public Stock(String symbol, String name, Country country) {
        this.symbol = symbol;
        this.name = name;
        this.country = country;
    }
}
