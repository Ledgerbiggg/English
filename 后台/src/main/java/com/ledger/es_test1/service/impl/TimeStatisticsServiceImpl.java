package com.ledger.es_test1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.domain.TimeStatistics;
import com.ledger.es_test1.mapper.TimeStatisticsMapper;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.TimeStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TimeStatisticsServiceImpl extends ServiceImpl<TimeStatisticsMapper, TimeStatistics> implements TimeStatisticsService {

    @Override
    public Result<String> postTimeToCount() {
        LocalDateTime now = LocalDateTime.now();
        return null;
    }
}
