package com.ledger.es_test1.response;


import lombok.Data;
import org.springframework.http.HttpStatus;


@Data

public class Result<T> {

    static final Result<String>  Fail=new Result<>(500,"","");
    static final Result<String>  SUCCESS=new Result<>(200,"","");

    private  HttpStatus code= HttpStatus.OK;
    private  String msg;
    private  T data;

    public Result(Integer code, String msg, T data) {
        this.code = HttpStatus.resolve(code);
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(500, "fail", data);
    }

}
