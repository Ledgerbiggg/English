package com.ledger.es_test1.vo;

import com.ledger.es_test1.domain.TimeStatistics;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeVo extends TimeStatistics {
    private String id;
    private Integer countTime;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private String hours;
}
