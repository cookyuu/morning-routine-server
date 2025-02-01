package com.cookyuu.morning_routine.batch.config;

import com.cookyuu.morning_routine.batch.crawling.indicators.tasklet.IndicatorsCrawlingTasklet;
import com.cookyuu.morning_routine.batch.crawling.stock.tasklet.StockCrawlingTasklet;
import com.cookyuu.morning_routine.domain.indicators.entity.IndicatorsType;
import com.cookyuu.morning_routine.domain.indicators.facade.IndicatorsCrawlingFacade;
import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.facade.StockCrawlingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CrawlingJobConfig {
    private final StockCrawlingFacade stockCrawlingFacade;
    private final IndicatorsCrawlingFacade indicatorsCrawlingFacade;

    @Bean(name = "usStockCrawlingJob")
    public Job usStockCrawlingJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("usStockCrawlingJob", jobRepository)
                .start(stockCrawlingStep(jobRepository, platformTransactionManager, Country.US))
                .build();
    }

    @Bean(name = "stockIndicatorsCrawlingJob")
    public Job stockIndicatorsCrawlingJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("stockIndicatorsCrawlingJob", jobRepository)
                .start(indicatorsCrawlingStep(jobRepository, platformTransactionManager, IndicatorsType.STOCK))
                .build();
    }

    @Bean
    public Step stockCrawlingStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, Country country) {
        StockCrawlingTasklet stockCrawlingTasklet = new StockCrawlingTasklet(stockCrawlingFacade);
        stockCrawlingTasklet.setCountry(country);
        return new StepBuilder("stockCrawlingStep", jobRepository)
                .tasklet(stockCrawlingTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    public Step indicatorsCrawlingStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, IndicatorsType indicatorsType) {
        IndicatorsCrawlingTasklet indicatorsCrawlingTasklet = new IndicatorsCrawlingTasklet(indicatorsCrawlingFacade);
        indicatorsCrawlingTasklet.setIndicatorsType(indicatorsType);
        return new StepBuilder("indicatorsCrawlingStep", jobRepository)
                .tasklet(indicatorsCrawlingTasklet, platformTransactionManager)
                .build();
    }
}
