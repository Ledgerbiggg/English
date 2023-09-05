package com.ledger.es_test1.controller;

import com.ledger.es_test1.domain.TimeStatistics;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.TimeStatisticsService;
import com.ledger.es_test1.vo.TimeVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeStatisticsController {

    @Resource
    private TimeStatisticsService timeStatisticsService;

    @PostMapping("/postTimeToCount")
    public Result<String> postTimeToCount(){
       return timeStatisticsService.postTimeToCount();
    }

    @PostMapping("/getRecentTime")
    public Result<List<TimeVo>> getRecentTime(Integer days){
        return timeStatisticsService.getRecentTime(days);
    }





















}
