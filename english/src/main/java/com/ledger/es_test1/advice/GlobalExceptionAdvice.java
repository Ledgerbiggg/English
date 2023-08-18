package com.ledger.es_test1.advice;

import com.ledger.es_test1.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    public Result<String> IOException(RuntimeException e) {
        return Result.fail(e.getMessage());
    }


}

