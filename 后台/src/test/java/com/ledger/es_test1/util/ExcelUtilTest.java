package com.ledger.es_test1.util;

import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.listener.ExcelListener;
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
class ExcelUtilTest {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void read() {
        ExcelUtil.read("D:\\360TEST\\ledger.xlsx", EnglishWords.class, "EnglishWord", new ExcelListener());
    }

    @Test
    void write() {
        stringRedisTemplate.opsForValue().set("excel", "1");

    }
}