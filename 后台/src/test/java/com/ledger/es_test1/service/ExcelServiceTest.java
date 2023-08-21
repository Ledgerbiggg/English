package com.ledger.es_test1.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelServiceTest {
    @Resource
    private ExcelService excelService;

    @Test
    void analyze() {
        excelService.remove(null);
    }
}