package com.cookyuu.morning_routine.batch.crawling.stock.job;

import com.cookyuu.morning_routine.batch.config.CrawlingJobConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableScheduling
public class StockCrawlingJob {
    private final JobLauncher jobLauncher;
    private final CrawlingJobConfig crawlingJobConfig;

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Scheduled(cron = "0 01 06 * * ?")
    public void usStockCrawlingJob() {
        try {
            log.info("[Batch-StockCrawling] Stock crawling job start, datetime : {} ", LocalDateTime.now());
            jobLauncher.run(
                    crawlingJobConfig.usStockCrawlingJob(jobRepository, platformTransactionManager),
                    new JobParametersBuilder().addString("dataTime", LocalDateTime.now().toString()).toJobParameters()
            );
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                 JobParametersInvalidException | JobRestartException e) {
            log.error("[USStockCrawlingJob] Exception : " + e);
        }
    }
}


