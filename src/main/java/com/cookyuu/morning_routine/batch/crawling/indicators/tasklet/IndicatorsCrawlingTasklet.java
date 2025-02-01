package com.cookyuu.morning_routine.batch.crawling.indicators.tasklet;

import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.facade.IndicatorsCrawlingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndicatorsCrawlingTasklet implements Tasklet {
    private final IndicatorsCrawlingFacade indicatorsCrawlingFacade;
    private IndicatorsType indicatorsType;

    public void setIndicatorsType(IndicatorsType indicatorsType) {
        this.indicatorsType = indicatorsType;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        indicatorsCrawlingFacade.crawlingIndicators(indicatorsType);
        return RepeatStatus.FINISHED;
    }
}
