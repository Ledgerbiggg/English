package com.ledger.es_test1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ledger
 * @version 1.0
 **/
@Configuration
public class ThreadPool {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
      return  new ThreadPoolExecutor(
                        1,
                        2,
                        2,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(3),
                        (r)->{
                            Thread thread = new Thread(r);
                            thread.setName("ledger");
                            return new Thread(r);
                        },
                        new ThreadPoolExecutor.AbortPolicy()
                );
    }
}
