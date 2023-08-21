package com.ledger.es_test1.service.impl;

import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.listener.ExcelListener;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ledger
 * @version 1.0
 **/
@Component
public class ExcelServiceAndListener {
    @Resource
    ExcelServiceImpl excelService;
    @Resource
    ExcelListener excelListener;
    @Resource
    public ThreadPoolExecutor threadPoolExecutor;

    @Value("${common.path}")
    public String path;
    @Value("${common.fileName}")
    public String fileName;
    @Value("${common.sheetName}")
    public String sheetName;

    public Result<String> analyze(MultipartFile file) {
//        Result<String> analyze = excelService.analyze(file);
        threadPoolExecutor.submit(()->{
            ExcelUtil.read(path+fileName,EnglishWords.class,sheetName,excelListener);
        });
       return null;
    }

}
