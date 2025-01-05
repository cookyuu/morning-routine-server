package com.cookyuu.morning_routine.domain.region.controller;

import com.cookyuu.morning_routine.domain.region.dto.RegisterRegionFromFileDto;
import com.cookyuu.morning_routine.domain.region.service.RegionService;
import com.cookyuu.morning_routine.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/registration/file")
    public ApiResponse<String> registerRegionFromFile() throws IOException {
        regionService.registerFromFile();
        return ApiResponse.success("지역 등록 완료.");
    }

}
