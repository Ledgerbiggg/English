package com.ledger.es_test1.util;

import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.listener.ExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ledger
 * @version 1.0
 **/
@SpringBootTest
@Slf4j
class ExcelUtilTest {

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    ExcelListener excelListener;

    @Test
    void read() {
        ExcelUtil.read("D:\\360TEST\\words2.xlsx", EnglishWords.class, "EnglishWord", excelListener);
    }

    @Test
    void write() {
        stringRedisTemplate.opsForValue().set("excel", "1");
    }
}