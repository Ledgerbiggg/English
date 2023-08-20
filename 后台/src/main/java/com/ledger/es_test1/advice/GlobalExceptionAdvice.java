package com.ledger.es_test1.advice;

import com.ledger.es_test1.Exception.KnowException;
import com.ledger.es_test1.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(KnowException.class)
    public Result<String> KnowException(KnowException e) {
        return Result.fail(e.getMessage());
    }

}

