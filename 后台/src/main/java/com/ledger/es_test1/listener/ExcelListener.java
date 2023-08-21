package com.ledger.es_test1.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.service.ExcelService;
import com.ledger.es_test1.util.EsUtil;
import com.ledger.es_test1.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class ExcelListener extends AnalysisEventListener<EnglishWords> {
    private static final ArrayList<EnglishWords> englishWordsList = new ArrayList<>();
    private static Map<Integer, String> hs;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    ExcelService excelService;
    @Resource
    ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void invoke(EnglishWords englishWords, AnalysisContext analysisContext) {
        //读取表格文件
        englishWords.setId(UUID.randomUUID().toString());
        englishWordsList.add(englishWords);
        log.info(englishWords + "");
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //读取表头
        hs = headMap;
        log.info(headMap + "");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //读完之后的操作
        //存入表头数据到redis
        //hs.forEach((k, v) -> stringRedisTemplate.opsForHash().put("excel", String.valueOf(k), v));
        //先删除数据库
        LambdaQueryWrapper<EnglishWords> wrapper = new LambdaQueryWrapper<>();
        excelService.remove(null);
        //存入表体数据到pgsql
        excelService.saveBatch(englishWordsList);
        //存入表体到es
        EsUtil.deleteAllDocs("english_words");
        EsUtil.insertDocsIntoEs("english_words", englishWordsList);
        englishWordsList.clear();
    }
}
