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
import org.springframework.stereotype.Component;

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

    public Page<StockListInfoDto> getWishList(long userId) {
        // wish list에 있는 user의 주식 데이터를 가지고 온다.
        // stock, stockIndex 데이터 가지고 오기
        // 가장 최근 데이터 가지고 오기

        return null;
    }

}
