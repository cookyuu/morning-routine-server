package com.cookyuu.morning_routine.domain.stock.controller;

import com.cookyuu.morning_routine.domain.stock.dto.AddWishStockInfoDto;
import com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto;
import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.facade.StockCrawlingFacade;
import com.cookyuu.morning_routine.domain.stock.facade.StockWishFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import com.cookyuu.morning_routine.global.security.jwt.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {

    private final StockCrawlingFacade stockCrawlingFacade;
    private final StockWishFacade stockWishFacade;

    @GetMapping("/crawling")
    public ApiResponse<String> crawlingStock(@RequestParam Country country) throws JsonProcessingException {
        stockCrawlingFacade.crawlingStock(country);
        return ApiResponse.success();
    }

    @PostMapping("/my/wishlist")
    public ApiResponse<String> addStockWishList(@AuthenticationPrincipal CustomUserDetails user, @RequestBody AddWishStockInfoDto stockInfo) {
        stockWishFacade.addStockWishList(Long.parseLong(user.getUsername()), stockInfo);
        return ApiResponse.success("주식 관심 목록 추가 완료.");
    }

    @GetMapping("/my/wishlist")
    public ApiResponse<Page<StockListInfoDto>> getStockWishList(@AuthenticationPrincipal CustomUserDetails user) {
        Page<StockListInfoDto> resWishStockData = stockWishFacade.getWishList(Long.parseLong(user.getUsername()));
        return ApiResponse.success();
    }

}
