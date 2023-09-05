package com.ledger.es_test1.service.impl;

import com.ledger.es_test1.domain.BookmarkedWord;
import com.ledger.es_test1.service.BookmarkedWordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookmarkedWordServiceImplTest {
    @Resource
    private BookmarkedWordService bookmarkedWordService;
    @Test
    void name() {
        List<BookmarkedWord> list = bookmarkedWordService.list();
        log.info("list"+list);
    }
}