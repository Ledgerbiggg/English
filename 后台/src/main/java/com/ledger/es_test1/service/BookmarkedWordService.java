package com.ledger.es_test1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ledger.es_test1.domain.BookmarkedWord;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.domain.TimeStatistics;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.vo.TimeVo;

import java.util.List;


public interface BookmarkedWordService extends IService<BookmarkedWord> {


    boolean saveByEnglishId(BookmarkedWord bookmarkedWord);

    List<EnglishWords> getEnglishWordsByBookmarkedWordList(List<BookmarkedWord> list,List<String> type);


    boolean removeEnglishWordId(String id);
}
