package com.cookyuu.morning_routine.batch.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class BatchJobConfig {

    @Bean(name = "usStockCrawlingJob")
    public Job usStockCrawlingJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("usStockCrawlingJob", jobRepository)
                .start(usStockCrawlingStep(jobRepository, platformTransactionManager))
                .build();
    }

    @Bean
    public Step usStockCrawlingStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        ItemReader reader = new USStockItemReader();
        ItemProcessor processor = new USStockItemProcessor();
        ItemWriter writer = new USStockItemWriter();

        return new StepBuilder("usStockCrawlingStep", jobRepository)
                .chunk(100, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
