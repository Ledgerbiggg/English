package com.ledger.es_test1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ledger.es_test1.mapper")
public class EsTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(EsTest1Application.class, args);
    }

}
