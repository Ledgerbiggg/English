package com.ledger.es_test1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledger.es_test1.domain.SecurityUser;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.mapper.UserMapper;
import com.ledger.es_test1.response.Result;

import com.ledger.es_test1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${jwt.secret}")
    private String secret;
    @Resource
    private PasswordEncoder passwordEncoder;

//    @Override
//    public Result<String> login(User user, HttpServletResponse response) {
//        String username = user.getUsername();
//        User userByUsername = getUserByUsername(username);
//        if (userByUsername == null) {
//            return Result.fail("用户名不存在");
//        }
//        if (passwordEncoder.matches(user.getPassword(), userByUsername.getPassword())) {
//            return Result.fail("密码错误");
//        }
//        Integer role = userByUsername.getRole();
//        Integer id = userByUsername.getId();
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("role", role);
//        response.setHeader("Authorization", "Bearer " + JwtUtil.createJwt(claims,secret, String.valueOf(id), 60));
//        return Result.success("登录成功");
//    }

    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }


}






















