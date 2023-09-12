package com.ledger.es_test1.filter;

import com.alibaba.fastjson.JSON;
import com.ledger.es_test1.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if ("/getCaptchaCode".equals(requestURI)) {
            validateCode(request, response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }
    private void validateCode(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String code1 = request.getParameter("code");
        session.removeAttribute("code_err");
        if(code1==null || "".equals(code1)) {
            session.setAttribute("code_err","验证码为空");
        }
        if(!code.equals(code1)) {
            session.setAttribute("code_err","验证码");
        }
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(Result.fail((String) session.getAttribute("code_err"))));
        if (code.equals(code1)) {
            session.removeAttribute("code");
            filterChain.doFilter(request, response);
        }
    }
}