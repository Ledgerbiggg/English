package com.ledger.es_test1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.domain.SecurityUser;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.mapper.UserMapper;
import com.ledger.es_test1.response.Result;

import com.ledger.es_test1.service.UserService;
import com.ledger.es_test1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Result<String> login(User user, HttpServletResponse response) {
        String username = user.getUsername();
        User userByUsername = getUserByUsername(username);
        if (userByUsername == null) {
            return Result.fail("用户名不存在");
        }
        if (passwordEncoder.matches(user.getPassword(), userByUsername.getPassword())) {
            return Result.fail("密码错误");
        }
        SecurityUser securityUser = new SecurityUser(userByUsername);
        String token = JwtUtil.createJwt(securityUser, secret);
        response.setHeader("Authorization", tokenHead + " " + token);
        return Result.success("登录成功", token);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecurityUser(getUserByUsername(username));
    }
}






















