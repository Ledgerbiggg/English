package com.ledger.es_test1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.domain.BookmarkedWord;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.mapper.BookmarkedWordMapper;
import com.ledger.es_test1.service.BookmarkedWordService;
import com.ledger.es_test1.service.ExcelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkedWordServiceImpl extends ServiceImpl<BookmarkedWordMapper, BookmarkedWord> implements BookmarkedWordService {

    @Resource
    private ExcelService excelService;

    @Override
    public boolean saveByEnglishId(BookmarkedWord bookmarkedWord) {

        LambdaQueryWrapper<BookmarkedWord> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BookmarkedWord::getEnglishWordId, bookmarkedWord.getEnglishWordId());

        long count = count(wrapper);

        boolean result = false;
        if (count > 0) {
            LambdaUpdateWrapper<BookmarkedWord> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(BookmarkedWord::getEnglishWordId, bookmarkedWord.getEnglishWordId());
            result = update(bookmarkedWord, updateWrapper);
        } else {
            result = save(bookmarkedWord);
        }
        return result;
    }

    @Override
    public List<EnglishWords> getEnglishWordsByBookmarkedWordList(List<BookmarkedWord> list, List<String> type) {
        boolean isAll = type.contains("所有单词");

        List<String> collect = list
                .stream()
                .map(BookmarkedWord::getEnglishWordId)
                .collect(Collectors.toList());

        LambdaQueryWrapper<EnglishWords> wrapper = new LambdaQueryWrapper<>();

        wrapper.in(EnglishWords::getId, collect).in(!isAll || type.isEmpty(), EnglishWords::getSort, type);

        return excelService.list(wrapper);
    }

    @Override
    public boolean removeEnglishWordId(String id) {

        LambdaQueryWrapper<BookmarkedWord> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BookmarkedWord::getEnglishWordId, id);

        return remove(wrapper);
    }
}
