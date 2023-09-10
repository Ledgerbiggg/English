package com.ledger.es_test1.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.domain.TimeStatistics;
import com.ledger.es_test1.mapper.TimeStatisticsMapper;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.TimeStatisticsService;
import com.ledger.es_test1.vo.TimeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TimeStatisticsServiceImpl extends ServiceImpl<TimeStatisticsMapper, TimeStatistics> implements TimeStatisticsService {
    @Resource
    private TimeStatisticsMapper timeStatisticsMapper;

    /**
     * 增加当天的一分钟的时间
     * @return 成功
     */
    @Override
    public Result<String> postTimeToCount() {
        LocalDate nowDate = LocalDate.now();

        LambdaQueryWrapper<TimeStatistics> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(TimeStatistics::getDate, nowDate);

        TimeStatistics one = getOne(wrapper);
        if (one != null) {
            LocalTime endTime = one.getEndTime();
            LocalTime nowEndTime = endTime.plusMinutes(1);
            String id = one.getId();
            LambdaUpdateWrapper<TimeStatistics> wrapper1 = new LambdaUpdateWrapper<>();
            wrapper1.set(TimeStatistics::getEndTime, nowEndTime)
                    .eq(TimeStatistics::getId, id)
                    .set(TimeStatistics::getCountTime, one.getCountTime() + 1);
            update(wrapper1);
        } else {
            TimeStatistics timeStatistics = new TimeStatistics();
            timeStatistics.setId(UUID.randomUUID().toString());
            timeStatistics.setDate(nowDate);
            LocalTime now = LocalTime.now();
            timeStatistics.setStartTime(now);
            timeStatistics.setEndTime(now);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String format = LocalDateTime.now().format(dateTimeFormatter);
            timeStatistics.setDescription(format);
            timeStatistics.setCountTime(1);
            save(timeStatistics);
        }
        return Result.success("成功");
    }

    @Override
    public Result<List<TimeVo>> getRecentTime(Integer days) {
        List<TimeStatistics> list = timeStatisticsMapper.getRecentTime(days);
        List<TimeVo> collect = list.stream().map(i -> {
            TimeVo timeVo = BeanUtil.copyProperties(i, TimeVo.class);
            int h = timeVo.getCountTime() / 60;
            int m = timeVo.getCountTime() % 60;
            timeVo.setHours(h + "时" + m + "分");
            return timeVo;
        }).collect(Collectors.toList());
        return Result.success(collect);
    }
}
