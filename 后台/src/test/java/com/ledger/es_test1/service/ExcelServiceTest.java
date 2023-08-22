package com.ledger.es_test1.service;

import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.vo.TableVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ExcelServiceTest {
    @Resource
    private ExcelService excelService;

    @Test
    void analyze() {
        excelService.remove(null);
    }

    @Test
    void testMethod2() {
        Result<TableVo>  all = excelService.getDataBySize2(50, "所有单词");

        TableVo data = all.getData();
        List<EnglishWords> tableBody = data.getTableBody();

        for (EnglishWords datum : tableBody) {
            log.info(datum.toString());
        }
    }

    @Test
    void get() {
        List<EnglishWords> list = excelService.list();
        list.forEach(System.out::println);
    }
}