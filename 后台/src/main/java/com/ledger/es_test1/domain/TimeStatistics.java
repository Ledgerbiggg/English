package com.ledger.es_test1.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TimeStatistics {
    private String id;
    private String countTime;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
}
