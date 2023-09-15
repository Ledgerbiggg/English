package com.ledger.es_test1.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.response.Result;

import javax.servlet.http.HttpServletResponse;

public interface UserService  extends IService<User> {

    Result<String> login(User user, HttpServletResponse response);

    User getUserByUsername(String username);
}




















