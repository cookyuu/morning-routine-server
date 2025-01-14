package com.cookyuu.morning_routine.domain.stock.repository;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.entity.StockWish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockWishRepository extends JpaRepository<StockWish, Long> {
    @Query("SELECT DISTINCT region FROM RegionInterest")
    List<Region> findWishStockByMemberOrderByCreatedAtDesc(long userId);

    boolean existsByStockAndMember(Stock stock, Member member);
}
