package com.ledger.es_test1;

import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class EsTest1ApplicationTests {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    UserService userService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 5; i++) {
            stringRedisTemplate.opsForHash().put("excel",i+"",i+"");
        }
    }

    @Test
    void name() {
        User userByUsername = userService.getUserByUsername("ledger");
        log.info(userByUsername.toString());




    }
}
