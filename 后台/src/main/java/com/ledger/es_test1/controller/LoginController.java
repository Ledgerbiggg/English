package com.ledger.es_test1.controller;

import com.alibaba.fastjson.JSON;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.response.Result;

import com.ledger.es_test1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/loginUser")
    public Result<String> login(@RequestBody User user, HttpServletResponse response) {
        // TODO 自己来写
        log.info("user" + user);
        return userService.login(user, response);
    }


}
