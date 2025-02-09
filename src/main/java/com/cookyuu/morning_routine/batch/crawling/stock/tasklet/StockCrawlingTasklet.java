package com.cookyuu.morning_routine.batch.crawling.stock.tasklet;

import com.cookyuu.morning_routine.domain.stock.entity.Country;
import com.cookyuu.morning_routine.domain.stock.facade.StockCrawlingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockCrawlingTasklet implements Tasklet {
    private final StockCrawlingFacade stockCrawlingFacade;
    private Country country;

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        stockCrawlingFacade.crawlingStock(country);
        return RepeatStatus.FINISHED;
    }
}
