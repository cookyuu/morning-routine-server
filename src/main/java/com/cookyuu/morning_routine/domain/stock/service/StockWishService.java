package com.cookyuu.morning_routine.domain.stock.service;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.entity.StockWish;
import com.cookyuu.morning_routine.domain.stock.repository.StockWishRepository;
import com.cookyuu.morning_routine.global.code.ResultCode;
import com.cookyuu.morning_routine.global.exception.domain.MRStockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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
    @Transactional(readOnly = true)
    public Page<StockListInfoDto> getWishList(Member member, Pageable pageable) {
        Page<StockListInfoDto> stockWishList = stockWishRepository.findWishStockByMemberOrderByCreatedAtDesc(member, pageable);
        for (StockListInfoDto stock : stockWishList) {
            log.info("[Stock-Wish] name : {}, country : {}, baseDate : {}", stock.getName(), stock.getCountry(), stock.getPriceBaseDate());
        }
        return stockWishList;
    }
}
