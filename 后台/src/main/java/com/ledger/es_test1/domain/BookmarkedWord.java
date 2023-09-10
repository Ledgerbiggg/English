package com.ledger.es_test1.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@TableName("bookmarked_words")
public class BookmarkedWord {
    @Id
    private String id;
    private String englishWordId;
    private LocalDate addDate;
}
