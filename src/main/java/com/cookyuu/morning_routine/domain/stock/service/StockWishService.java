package com.cookyuu.morning_routine.domain.stock.service;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.entity.StockWish;
import com.cookyuu.morning_routine.domain.stock.repository.StockWishRepository;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockWishService {

    private final StockWishRepository stockWishRepository;


    @Transactional
    public void addStockWishList(Member member, Stock stock) {
        if (stockWishRepository.existsByStockAndMember(stock, member)) {
            throw new MRStockException(ResultCode.WISH_STOCK_ALREADY);
        }
        stockWishRepository.save(StockWish.builder()
                        .member(member).stock(stock).build());

    }
}
