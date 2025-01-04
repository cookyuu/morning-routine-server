package com.cookyuu.morning_routine.domain.indicators.service;

import com.cookyuu.morning_routine.domain.indicators.dto.IndicatorsInfoDto;
import com.cookyuu.morning_routine.domain.indicators.entity.Indicators;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsIndex;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.repository.IndicatorsIndexRepository;
import com.cookyuu.morning_routine.domain.indicators.repository.IndicatorsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockIndicatorsCrawlingService implements IndicatorsCrawlingService{
    private final IndicatorsRepository indicatorsRepository;
    private final IndicatorsIndexRepository indicatorsIndexRepository;

    @Override
    @Transactional
    public void saveCrawlingData(List<IndicatorsInfoDto> dataList) {
        List<IndicatorsIndex> indicatorsIndexList = new ArrayList<>();
        for (IndicatorsInfoDto data : dataList) {
            Indicators indicators = data.getIndicatorsInfo().toEntity();
            if (!indicatorsRepository.existsById(indicators.getSymbol())) {
                indicators = indicatorsRepository.save(indicators);
            }
            IndicatorsIndex indicatorsIndex = data.getIndexInfo().toEntity(indicators);
            indicatorsIndexList.add(indicatorsIndex);
        }
        indicatorsIndexRepository.saveAll(indicatorsIndexList);
        log.info("[Crawling::StockIndicators] Save Stock indicators data complete.");
    }

    @Override
    public IndicatorsType getIndicatorsType() {
        return IndicatorsType.STOCK;
    }
}
