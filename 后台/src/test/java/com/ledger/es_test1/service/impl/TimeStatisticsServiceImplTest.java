package com.ledger.es_test1.service.impl;

import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.TimeStatisticsService;
import com.ledger.es_test1.vo.TimeVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TimeStatisticsServiceImplTest {

    @Resource
    private TimeStatisticsService timeStatisticsService;

    @Test
    void postTimeToCount() {
        Result<String> stringResult = timeStatisticsService.postTimeToCount();
        log.info(stringResult+"");
    }

    @Test
    void testPostTimeToCount() {

    }

    @Test
    void getRecentTime() {
        Result<List<TimeVo>> recentTime = timeStatisticsService.getRecentTime(7);
        log.info(recentTime+"");
    }
}