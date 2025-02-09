package com.cookyuu.morning_routine.domain.stock.repository;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.entity.StockWish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockWishRepository extends JpaRepository<StockWish, Long> {

    @Query("""
        SELECT new com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto(
            a.symbol,
            a.name,
            a.country,
            b.marketCap,
            b.priceBaseDate,
            b.oriHasPositiveStock,
            b.oriClosingPrice,
            b.oriChangePercent,
            b.oriChangePrice,
            b.afterLoadCompleted,
            b.afterHasPositiveStock,
            b.afterClosingPrice,
            b.afterChangePrice,
            b.afterChangePercent
        )
        FROM StockWish as w
        LEFT JOIN Stock as a ON w.stock = a
        LEFT JOIN StockIndex as b ON a = b.stock
        WHERE b.priceBaseDate = (
              SELECT MAX(b2.priceBaseDate)
              FROM StockIndex b2
              WHERE b2.stock = b.stock
          ) and member = :member
        ORDER BY w.createdAt DESC
    """)
    Page<StockListInfoDto> findWishStockByMemberOrderByCreatedAtDesc(@Param("member") Member member, Pageable pageable);

    boolean existsByStockAndMember(Stock stock, Member member);
}
