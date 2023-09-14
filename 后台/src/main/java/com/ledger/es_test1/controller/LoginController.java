package com.ledger.es_test1.controller;

import com.alibaba.fastjson.JSON;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.response.Result;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LoginController {

//    @Resource
//    private UserService userService;

    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response){
        // Your login logic here

        // If login is successful, set the response status code to 200 and return a success message.
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JSON.toJSONString(Result.success("4515")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
