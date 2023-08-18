package com.ledger.es_test1.controller;

import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.ExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ExcelController {
    @Resource
    private ExcelService excelService;

    @GetMapping("/hello")
    public Result<String> getHello(){
        return Result.success("你好");
    }

    @PostMapping("/saveAllExcelList")
    public Result<List<Integer>> saveAllExcelList(@RequestBody MultipartFile file){
        return excelService.analyze(file);
    }

//    @PostMapping("/getRandomData")
//    public Result<List<Integer>> getRandomData(Integer size){
//        return excelService.get();
//    }



}
