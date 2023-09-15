package com.ledger.es_test1.controller;

import com.alibaba.fastjson.JSON;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.response.Result;

import com.ledger.es_test1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user, HttpServletResponse response){
       return userService.login(user, response);
    }

}
