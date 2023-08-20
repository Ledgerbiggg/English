package com.ledger.es_test1.vo;

import com.ledger.es_test1.domain.EnglishWords;
import lombok.Data;

import java.util.List;

/**
 * @author ledger
 * @version 1.0
 **/
@Data
public class TableVo {
    private List<String> tableHead;
    private List<EnglishWords> tableBody;
}
