package com.cookyuu.morning_routine.batch.crawling.step;

import com.cookyuu.morning_routine.batch.crawling.indicators.tasklet.IndicatorsCrawlingTasklet;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.facade.IndicatorsCrawlingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@RequiredArgsConstructor
public class IndicatorsCrawlingStep {

    private final IndicatorsCrawlingFacade indicatorsCrawlingFacade;

    @Bean
    public Step stockIndicatorsCrawlingStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        IndicatorsCrawlingTasklet indicatorsCrawlingTasklet = new IndicatorsCrawlingTasklet(indicatorsCrawlingFacade);
        indicatorsCrawlingTasklet.setIndicatorsType(IndicatorsType.STOCK);
        return new StepBuilder("indicatorsCrawlingStep", jobRepository)
                .tasklet(indicatorsCrawlingTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    public Step exchangeIndicatorsCrawlingStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        IndicatorsCrawlingTasklet indicatorsCrawlingTasklet = new IndicatorsCrawlingTasklet(indicatorsCrawlingFacade);
        indicatorsCrawlingTasklet.setIndicatorsType(IndicatorsType.EXCHANGE_RATE);
        return new StepBuilder("indicatorsCrawlingStep", jobRepository)
                .tasklet(indicatorsCrawlingTasklet, platformTransactionManager)
                .build();
    }
}
