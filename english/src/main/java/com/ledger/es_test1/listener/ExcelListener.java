package com.ledger.es_test1.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

import java.util.Map;

@SuppressWarnings("all")
@Component
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ExcelListener extends AnalysisEventListener<EnglishWords> {
    private static ArrayList<EnglishWords> englishWordsList=new ArrayList<>();
    private static Map<Integer,String> hs;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    ExcelService excelService;

    @Override
    public void invoke(EnglishWords englishWords, AnalysisContext analysisContext) {
        //读取表格文件
        englishWordsList.add(englishWords);
        log.info(englishWords+"");
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //读取表头
        hs=headMap;
        log.info(headMap+"");

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //读完之后的操作
        //存入表头数据到redis
        hs.forEach((k,v)->stringRedisTemplate.opsForHash().put("excel",String.valueOf(k),v));
        //存入表体数据到pgsql
        excelService.saveBatch(englishWordsList);
    }
}
