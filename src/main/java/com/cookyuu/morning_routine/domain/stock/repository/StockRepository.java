package com.cookyuu.morning_routine.domain.stock.repository;

import com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findBySymbol(String symbol);

    @Query("""
        select new com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto(
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
        from Stock AS a
        left join StockIndex as b
        on a = b.stock
        where b.priceBaseDate = (
                SELECT MAX(b2.priceBaseDate) FROM StockIndex b2
            ) and b.marketCap is not null
        order by b.marketCap desc
    """)
    List<StockListInfoDto> findTopFiveStocksByLatestDateAndMarketCap(Pageable pageable);
}
