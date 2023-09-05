package com.ledger.es_test1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ledger.es_test1.domain.TimeStatistics;
import com.ledger.es_test1.mapper.TimeStatisticsMapper;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.vo.TimeVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TimeStatisticsService extends IService<TimeStatistics> {

    Result<String> postTimeToCount();

    Result<List<TimeVo>> getRecentTime(Integer days);

}
