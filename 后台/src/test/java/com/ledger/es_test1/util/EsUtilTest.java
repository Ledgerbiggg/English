package com.ledger.es_test1.util;

import com.ledger.es_test1.config.ThreadPool;
import com.ledger.es_test1.domain.EnglishWords;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ledger
 * @version 1.0
 **/
@SpringBootTest
@Slf4j
class EsUtilTest {
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    void searchDocsFromEs() {
    }

    @Test
    void insertDocsIntoEs() {
        ArrayList<EnglishWords> englishWords = new ArrayList<>();
        englishWords.add(new EnglishWords("1541414", "english_words", "1", "english_words", "english_words", "english_words", "english_words"));
        EsUtil.insertDocsIntoEs("english_words", englishWords);
    }

    @Test
    void deleteDocFromEs() {
    }

    @Test
    void updateDocInEs() {
    }

    @Test
    void deleteIndex() {
        Boolean english_words = EsUtil.deleteIndex("english_words");
        assert english_words;
    }

    @Test
    void createIndex() {
        Boolean english_words = EsUtil.createIndex("english_words");
        log.info(english_words + "");
    }

    @Test
    void test() {
        List<EnglishWords> englishWords = EsUtil.searchWordByPrefix("english_words", "wo", "word", EnglishWords.class);
        englishWords.forEach(System.out::println);
    }

    @Test
    void deleteAllDocs() {
        Boolean english_words = EsUtil.deleteAllDocs("english_words");
        assert english_words.equals(true);
    }
}