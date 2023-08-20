package com.ledger.es_test1.service.impl;

import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.util.EsUtil;
import com.ledger.es_test1.vo.TableVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@Slf4j
class ExcelServiceImplTest {

    @Resource
    ExcelServiceImpl excelService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void analyze() {
        ArrayList<Object> list = new ArrayList<>();
        Collections.addAll(list, "0", "1", "2", "3", "4", "5", "6");
        Object tableHead = stringRedisTemplate
                .opsForHash()
                .multiGet("excel", list);
        System.out.println(tableHead);

    }

    @Test
    void test() {
        Result<List<EnglishWords>> result = excelService.matchCNPrefix("工作");

        result.getData().forEach(System.out::println);
    }

    @Test
    void test2() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("explanation","工作");
        hashMap.put("word","work");
        List<EnglishWords> english_words = EsUtil.SearchDocsFromEs("english_words", EnglishWords.class, 0, 10, hashMap,null);
        english_words.forEach(System.out::println);
    }
}