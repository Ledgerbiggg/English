package com.ledger.es_test1.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.DeleteById;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.Exception.KnowException;
import com.ledger.es_test1.domain.EnglishWords;
import com.ledger.es_test1.listener.ExcelListener;
import com.ledger.es_test1.mapper.ExcelMapper;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.ExcelService;
import com.ledger.es_test1.util.EsUtil;
import com.ledger.es_test1.util.ExcelUtil;
import com.ledger.es_test1.vo.TableVo;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.IOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper, EnglishWords> implements ExcelService {

    @Value("${common.path}")
    public String path;
    @Value("${common.fileName}")
    public String fileName;
    @Resource
    public StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<String> analyze(MultipartFile file) {
        try {
            //获取流的信息
            InputStream inputStream = file.getInputStream();
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fullName = path + fileName;
            OutputStream outputStream = new FileOutputStream(fullName);
            IOUtil.copy(inputStream, outputStream, 1024);
            inputStream.close();
            outputStream.close();
            return Result.success("文件处理成功");
        } catch (IOException e) {
            e.printStackTrace();
            throw new KnowException("文件读取不成功");
        }
    }

    @Override
    public Result<TableVo> getDataBySize(Integer size, String type) {
        ArrayList<Object> list = new ArrayList<>();
        Collections.addAll(list, "0", "1", "2", "3", "4", "5", "6");
        //获取redis里面0-6的值
        List<String> tableHead = stringRedisTemplate
                .opsForHash()
                .multiGet("excel", list)
                .stream()
                .map(o -> (String) o)
                .collect(Collectors.toList());
        LambdaQueryWrapper<EnglishWords> wrapper = new LambdaQueryWrapper<>();
        if (size == null || size == 0) {
            throw new KnowException("长度不能为0或者空，你有什么大病吗");
        }
        List<EnglishWords> tableBody = list();
        Collections.shuffle(tableBody);
        List<EnglishWords> collect = tableBody
                .stream()
                .filter(o -> type.equals("所有单词") || o.getSort().equals(type))
                .limit(size)
                .collect(Collectors.toList());
        TableVo tableVo = new TableVo();
        tableVo.setTableHead(tableHead);
        tableVo.setTableBody(collect);
        stringRedisTemplate.opsForValue().set("cache", JSON.toJSONString(tableVo));
        Result<TableVo> success = Result.success(tableVo);
        log.info("获取数据成功{}", success);
        return success;
    }

    @Override
    public Result<String> deleteOneItem(Integer id) {
        String cache = stringRedisTemplate.opsForValue().get("cache");
        TableVo tableVo = JSON.parseObject(cache, TableVo.class);
        List<EnglishWords> tableBody;
        if (tableVo != null) {
            tableBody = tableVo.getTableBody();
            tableBody.removeIf(o -> o.getId().equals(id));
        }
        stringRedisTemplate.opsForValue().set("cache", JSON.toJSONString(tableVo));
        removeById(id);
        return Result.success("删除成功");
    }

    @Override
    public Result<TableVo> getCacheData() {
        String cache = stringRedisTemplate.opsForValue().get("cache");
        if (StrUtil.isBlank(cache)) {
            return Result.fail(null);
        }
        TableVo tableVo = JSON.parseObject(cache, TableVo.class);
        return Result.success(tableVo);
    }

    @Override
    public Result<List<EnglishWords>> matchWordPrefix(String prefix) {
        return Result.success(EsUtil.searchWordByPrefix("english_words", prefix, "word", EnglishWords.class));
    }

    @Override
    public Result<List<EnglishWords>> matchCNPrefix(String keyword) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("explanation", keyword);
        List<EnglishWords> english_words = EsUtil.SearchDocsFromEs("english_words",
                EnglishWords.class,
                0,
                10,
                hashMap,
                null);
        return Result.success(english_words);
    }
}
