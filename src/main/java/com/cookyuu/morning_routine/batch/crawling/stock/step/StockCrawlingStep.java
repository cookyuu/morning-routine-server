package com.cookyuu.morning_routine.batch.crawling.stock.step;

import com.cookyuu.morning_routine.batch.crawling.indicators.tasklet.IndicatorsCrawlingTasklet;
import com.cookyuu.morning_routine.batch.crawling.stock.tasklet.StockCrawlingTasklet;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.facade.IndicatorsCrawlingFacade;
import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.facade.StockCrawlingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@RequiredArgsConstructor
public class StockCrawlingStep {

    private final StockCrawlingFacade stockCrawlingFacade;

    @Bean
    public Step usStockCrawlingStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        StockCrawlingTasklet stockCrawlingTasklet = new StockCrawlingTasklet(stockCrawlingFacade);
        stockCrawlingTasklet.setCountry(Country.US);
        return new StepBuilder("usStockCrawlingStep", jobRepository)
                .tasklet(stockCrawlingTasklet, platformTransactionManager)
                .build();
    }
}
