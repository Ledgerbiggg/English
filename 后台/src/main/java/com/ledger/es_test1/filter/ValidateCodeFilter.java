package com.ledger.es_test1.filter;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.es_test1.domain.User;
import com.ledger.es_test1.response.Result;
import com.ledger.es_test1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Objects;

@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Resource
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if ("/login".equals(requestURI)) {
            validateCode(request, response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void validateCode(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String code1 = request.getParameter("code");
        session.removeAttribute("code_err");
        if (code1 == null || code == null) {
            session.setAttribute("code_err", "验证码为空");
        }
        if (!Objects.equals(code, code1)) {
            session.setAttribute("code_err", "验证码不正确");
        }
        if (!Objects.equals(code, code1) && code1 != null) {
            //如果验证码不一样就返回错误
            log.info("验证码不正确");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(Result.fail((String) session.getAttribute("code_err"),403)));
            writer.flush();
        }else {
            //登录成功要把session里面的code删除
            session.removeAttribute("code");
            //进入login
            log.info("验证码正确");
            InputStream inputStream = request.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();

            // 从请求的InputStream中读取JSON数据并反序列化为User对象
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            userService.login(user, response);
            //filterChain.doFilter(request, response);
        }
    }
}