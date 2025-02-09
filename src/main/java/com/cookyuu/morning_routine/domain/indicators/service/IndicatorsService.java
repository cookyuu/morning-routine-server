package com.cookyuu.morning_routine.domain.indicators.service;

import com.cookyuu.morning_routine.domain.indicators.repository.IndicatorsIndexRepository;
import com.cookyuu.morning_routine.domain.indicators.repository.IndicatorsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndicatorsService {
    private final IndicatorsRepository indicatorsRepository;
    private final IndicatorsIndexRepository indicatorsIndexRepository;

}
