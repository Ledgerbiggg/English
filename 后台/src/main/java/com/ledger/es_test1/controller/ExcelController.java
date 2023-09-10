package com.ledger.es_test1.controller;

import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.ExcelService;
import com.ledger.es_test1.service.impl.ExcelServiceAndListener;
import com.ledger.es_test1.vo.TableVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/english")
public class ExcelController {
    @Resource
    private ExcelServiceAndListener excelServiceAndListener;
    @Resource
    private ExcelService excelService;

    @GetMapping("/hello")
    public Result<String> getHello(){
        return Result.success("你好");
    }

    @PostMapping("/saveAllExcelList")
    public Result<String> saveAllExcelList(@RequestBody MultipartFile file){
        return excelServiceAndListener.analyze(file);
    }

    @GetMapping("/getRandomData")
    public Result<TableVo> getRandomData(Integer size,String type){
        String[] split = type.split(",");
        return excelService.getDataBySize2(size, Arrays.asList(split));
    }
    @GetMapping("/getCacheData")
    public Result<TableVo> getCacheData(){
        return excelService.getCacheData();
    }

    @DeleteMapping("/deleteOneItem")
    public Result<String> deleteOneItem(Integer id){
        return excelService.deleteOneItem(id);
    }

    @GetMapping("/matchWordPrefix")
    public Result<List<EnglishWords>> matchWordPrefix(String prefix){
       return excelService.matchWordPrefix(prefix);
    }

    @GetMapping("/matchCNPrefix")
    public Result<List<EnglishWords>> matchCNPrefix(String keyword){
        return excelService.matchCNPrefix(keyword);
    }
}
