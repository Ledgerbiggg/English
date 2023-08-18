package com.ledger.es_test1.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ledger.es_test1.domain.EnglishWords;

import java.util.List;

public class ExcelUtil {


    public static <T> void read(String PathAndFileName, Class<T> tClass,String sheetName, AnalysisEventListener<T> listener){
        EasyExcel.read(PathAndFileName, tClass,listener).sheet(sheetName).doRead();
    }

    public static <T> void write(String PathAndFileName, Class<T> tClass, String sheetName, List<T> dataList){
        EasyExcel.write(PathAndFileName, tClass).sheet(sheetName).doWrite(dataList);
    }

}
