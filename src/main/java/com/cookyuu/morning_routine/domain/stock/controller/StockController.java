package com.cookyuu.morning_routine.domain.stock.controller;

import com.cookyuu.morning_routine.domain.stock.dto.AddWishStockInfoDto;
import com.cookyuu.morning_routine.domain.stock.dto.StockListInfoDto;
import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.entity.Stock;
import com.cookyuu.morning_routine.domain.stock.facade.StockCrawlingFacade;
import com.cookyuu.morning_routine.domain.stock.facade.StockWishFacade;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import com.cookyuu.morning_routine.global.security.jwt.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ApiResponse<List<StockListInfoDto>> getStockWishList(@AuthenticationPrincipal CustomUserDetails user, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<StockListInfoDto> resWishStockData = stockWishFacade.getWishList(Long.parseLong(user.getUsername()), pageable);
        return ApiResponse.success(resWishStockData);
    }

    @GetMapping("/top-five")
    public ApiResponse<List<StockListInfoDto>> getStockTopFiveList() {
        List<StockListInfoDto> resStockList = stockWishFacade.getTopFiveList();
        return ApiResponse.success(resStockList);
    }

}
