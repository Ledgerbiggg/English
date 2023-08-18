package com.ledger.es_test1.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.listener.ExcelListener;
import com.ledger.es_test1.mapper.ExcelMapper;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.ExcelService;
import com.ledger.es_test1.util.ExcelUtil;
import org.codehaus.plexus.util.IOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@SuppressWarnings("all")
@Service
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper,EnglishWords> implements ExcelService {

    @Value("${common.path}")
    public String path;
    @Value("${common.fileName}")
    public String fileName;
    @Value("${common.sheelName}")
    public String sheelName;


    @Override
    public Result analyze(MultipartFile file) {
        try {
            //获取流的信息
            InputStream inputStream = file.getInputStream();
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fullName = path + fileName;
            OutputStream outputStream = new FileOutputStream(fullName);
            IOUtil.copy(inputStream,outputStream,1024);
            inputStream.close();
            outputStream.close();
            ExcelUtil.read(fullName,EnglishWords.class,sheelName,new ExcelListener());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件读取不成功");
        }
        return Result.success("文件处理成功");
    }






}
