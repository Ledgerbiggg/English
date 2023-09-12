package com.ledger.es_test1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.domain.SecurityUser;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.mapper.UserMapper;
import com.ledger.es_test1.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username==null || username.equals("")){
            throw new UsernameNotFoundException("用户名为空");
        }
        if(!username.equals("ledger")){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new SecurityUser(new User());
    }
}






















