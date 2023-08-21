package com.ledger.es_test1.domain;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(15)
@HeadRowHeight(20)
@TableName("english_words")
public class EnglishWords {

    @ColumnWidth(7)
    @ExcelProperty(value = "序号",index = 0)
    private String id;

    @ColumnWidth(7)
    @ExcelProperty(value = "分类",index = 1)
    private String sort;

    @ColumnWidth(7)
    @ExcelProperty(value = "list",index = 2)
    private String list;

    @ColumnWidth(7)
    @ExcelProperty(value = "号",index = 3)
    private String num;

    @ColumnWidth(20)
    @ExcelProperty(value = "单词",index = 4)
    private String word;

    @ColumnWidth(20)
    @ExcelProperty(value = "音标",index = 5)
    private String phonetic_symbol;

    @ColumnWidth(60)
    @ExcelProperty(value = "汉语解释",index = 6)
    private String explanation;

}
