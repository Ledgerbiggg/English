package com.ledger.es_test1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ledger.es_test1.domain.EnglishWords;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelMapper extends BaseMapper<EnglishWords> {

}
