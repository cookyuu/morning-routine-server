package com.cookyuu.morning_routine.domain.stock.facade;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.member.service.MemberService;
import com.cookyuu.morning_routine.domain.stock.dto.AddWishStockInfoDto;
import com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.service.StockService;
import com.cookyuu.morning_routine.domain.stock.service.StockWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockWishFacade {
    private final StockWishService stockWishService;
    private final StockService stockService;
    private final MemberService memberService;

    public void addStockWishList(long userId, AddWishStockInfoDto stockInfo) {
        Member  member = memberService.getMemberById(userId);
        Stock stock = stockService.findBySymbol(stockInfo.getSymbol());
        stockWishService.addStockWishList(member, stock);
    }

    public Page<StockListInfoDto> getWishList(Long userId, Pageable pageable) {
        Member member = memberService.getMemberById(userId);
        return stockWishService.getWishList(member, pageable);
    }

    public List<StockListInfoDto> getTopFiveList() {
        return stockService.getTopFiveStock();
    }
}
