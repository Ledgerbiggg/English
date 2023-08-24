package com.ledger.es_test1.controller;

import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.TimeStatisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TimeStatisticsController {

    @Resource
    private TimeStatisticsService timeStatisticsService;

    @PostMapping("/postTimeToCount")
    public Result<String> postTimeToCount(){
       return timeStatisticsService.postTimeToCount();
    }


}
