package com.ledger.es_test1.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.response.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService extends IService<EnglishWords> {
    Result<List<Integer>> analyze(MultipartFile file);
}
