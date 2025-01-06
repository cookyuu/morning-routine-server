package com.cookyuu.morning_routine.domain.region.controller;

import com.cookyuu.morning_routine.domain.region.dto.RegisterInterestRegionDto;
import com.cookyuu.morning_routine.domain.region.facade.RegionFacade;
import com.cookyuu.morning_routine.domain.region.service.RegionService;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/region")
public class RegionController {
    private final RegionService regionService;
    private final RegionFacade regionFacade;

    @PostMapping("/registration/file")
    public ApiResponse<String> registerRegionFromFile() throws IOException {
        regionService.registerFromFile();
        return ApiResponse.success("지역 등록 완료.");
    }

    @PostMapping("/interest")
    public ApiResponse<String> registerInterestRegion(@AuthenticationPrincipal UserDetails user, @RequestBody RegisterInterestRegionDto regionInfo) {
        regionFacade.registerInterestRegion(regionInfo, Long.valueOf(user.getUsername()));
        return ApiResponse.success("관심 지역 등록 완료.");
    }
}
