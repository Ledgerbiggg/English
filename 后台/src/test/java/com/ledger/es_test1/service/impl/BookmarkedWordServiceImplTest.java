package com.ledger.es_test1.service.impl;

import com.ledger.es_test1.domain.BookmarkedWord;
import com.ledger.es_test1.service.BookmarkedWordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Test
    void count() {
        long count = bookmarkedWordService.count();
        log.info("count"+count);
    }

    @Test
    void pwd() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String ledger = bCryptPasswordEncoder.encode("ledger");
        System.out.println("Encoded Password: " + ledger);
    }

    @Test
    void check() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean ledger = bCryptPasswordEncoder.matches("ledger", "$2a$10$4v6Tj/ejtHsJBVjDQy0D8On/xc.Fl1jd5uK.xA7Ap45961KfjVkr2");
        log.info(ledger+"");

    }
}