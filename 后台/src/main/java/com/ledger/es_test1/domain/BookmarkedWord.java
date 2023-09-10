package com.ledger.es_test1.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@TableName("bookmarked_words")
public class BookmarkedWord {
    @TableId
    private String id;
    private String englishWordId;
    private String explanation;
    private String word;
    private LocalDate addDate;

}
