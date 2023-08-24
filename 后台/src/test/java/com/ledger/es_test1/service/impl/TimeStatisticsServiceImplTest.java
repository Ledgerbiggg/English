package com.ledger.es_test1.service.impl;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeStatisticsServiceImplTest {

    @Test
    void postTimeToCount() {
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
        int minute = now.getMinute();
        System.out.println(now);
    }
}