package com.ledger.es_test1.controller;

import com.ledger.es_test1.domain.BookmarkedWord;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.BookmarkedWordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bookmarkedWord")
public class BookmarkedWordController {

    @Resource
    private BookmarkedWordService bookmarkedWordService;

    @GetMapping("/getAllBookmarkedWord")
    public Result<List<EnglishWords>> getAllBookmarkedWord(int size, String type){
        String[] split = type.split(",");
        List<BookmarkedWord> list = bookmarkedWordService.list();
        return Result.success(bookmarkedWordService.getEnglishWordsByBookmarkedWordList(list, Arrays.asList(split)));
    }

    @PostMapping("/saveOneBookmarkedWord")
    @PreAuthorize("hasAuthority('admin')")
    public Result<String> saveOneBookmarkedWord(@RequestBody BookmarkedWord bookmarkedWord){
        boolean save = bookmarkedWordService.saveByEnglishId(bookmarkedWord);
        return Result.success(save ? "保存成功" : "保存失败");
    }

    @DeleteMapping("/deleteOneBookmarkedWord")
    @PreAuthorize("hasAuthority('admin')")
    public Result<String> deleteOneBookmarkedWord(String id){
        return Result.success(bookmarkedWordService.removeEnglishWordId(id)?"删除成功":"删除失败");
    }

}
