package com.ledger.es_test1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class EsTest1ApplicationTests {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        for (int i = 0; i < 5; i++) {
            stringRedisTemplate.opsForHash().put("excel",i+"",i+"");
        }
    }

}
