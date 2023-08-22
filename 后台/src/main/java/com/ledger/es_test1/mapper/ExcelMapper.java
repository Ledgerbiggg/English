package com.ledger.es_test1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ledger.es_test1.domain.EnglishWords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExcelMapper extends BaseMapper<EnglishWords> {

    List<EnglishWords> getDataBySize2(@Param("size") Integer size, @Param("sort") String sort);
}
