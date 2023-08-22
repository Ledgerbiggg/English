package com.ledger.es_test1.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.vo.TableVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ExcelService extends IService<EnglishWords> {
    Result<String> analyze(MultipartFile file);

    Result<TableVo> getDataBySize(Integer size,String type);

    Result<TableVo>  getDataBySize2(Integer size,String type);

    Result<String> deleteOneItem(Integer id);

    Result<TableVo> getCacheData();

    Result<List<EnglishWords>> matchWordPrefix(String prefix);

    Result<List<EnglishWords>> matchCNPrefix(String keyword);


}
