package com.ledger.es_test1.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@TableName("time_statistics")
public class TimeStatistics {
    @Id
    private String id;
    private Integer countTime;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
}
