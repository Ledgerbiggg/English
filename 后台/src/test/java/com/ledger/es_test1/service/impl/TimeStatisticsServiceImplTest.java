package com.ledger.es_test1.service.impl;

import com.ledger.es_test1.domain.TimeStatistics;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.TimeStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TimeStatisticsServiceImplTest {

    @Resource
    private TimeStatisticsService timeStatisticsService;

    @Test
    void postTimeToCount() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        Month month = now.getMonth();
        int dayOfMonth = now.getDayOfMonth();
        log.info(year+"==="+month.getValue()+"==="+dayOfMonth);

    }

    @Test
    void testSave() {
//        private String id;
//        private String countTime;
//        private LocalDate date;
//        private LocalTime start;
//        private LocalTime end;
//        private String description;
        TimeStatistics timeStatistics = new TimeStatistics();
        timeStatistics.setId("4125");
        timeStatistics.setCountTime(1);
        timeStatistics.setDate(LocalDate.now());
        timeStatistics.setStartTime(LocalTime.now());
        timeStatistics.setEndTime(LocalTime.now());
        timeStatistics.setDescription("6453515");
        timeStatisticsService.saveOrUpdate(timeStatistics);

//        timeStatisticsService.getOne();

    }

    @Test
    void add() {
        Result<String> stringResult = timeStatisticsService.postTimeToCount();
    }
}