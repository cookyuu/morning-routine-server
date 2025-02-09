package com.cookyuu.morning_routine.domain.stock.entity;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_STOCK_WISH")
public class StockWish extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_wish_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "stock_symbol")
    private Stock stock;

    @Builder
    public StockWish (Member member, Stock stock) {
        this.member = member;
        this.stock = stock;
    }
}
