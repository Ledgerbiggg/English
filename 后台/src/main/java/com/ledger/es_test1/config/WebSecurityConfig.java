package com.ledger.es_test1.config;

import com.ledger.es_test1.domain.SecurityUser;
import com.ledger.es_test1.filter.JwtAuthenticationTokenFilter;
import com.ledger.es_test1.filter.ValidateCodeFilter;
import com.ledger.es_test1.handler.AppAccessDeniedHandler;
import com.ledger.es_test1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {


    @Resource
    private AppAccessDeniedHandler appAccessDeniedHandler;

    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private ValidateCodeFilter validateCodeFilter;

    @Resource
    private UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 禁用跨域资源共享 (CORS) 功能
                .cors().disable()
                // 在 UsernamePasswordAuthenticationFilter 之前添加自定义验证码过滤器
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 在 UsernamePasswordAuthenticationFilter 之前添加JWT认证令牌过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 配置权限控制规则
                .authorizeRequests()
                // 任何请求都需要认证通过（需要登录）
                .anyRequest()
                .authenticated()
                .and()
                // 禁用跨站请求伪造 (CSRF) 保护
                .csrf().disable()
                // 配置HTTP响应头部，包括缓存控制(禁用缓存)
                .headers()
                .disable()
                //禁用session 的记住用户的功能
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 配置异常处理 - 设置身份验证入口点 (AuthenticationEntryPoint)
        httpSecurity
                .exceptionHandling()
                // 设置身份验证入口点，用于处理未经身份验证的请求
                .authenticationEntryPoint(authenticationEntryPoint);

        // 配置异常处理 - 设置访问被拒绝的处理程序 (AccessDeniedHandler)
        httpSecurity
                .exceptionHandling()
                // 设置访问被拒绝的处理程序，用于处理未经授权的请求
                .accessDeniedHandler(appAccessDeniedHandler);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/captcha", "/login");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return name -> new SecurityUser(userService.getUserByUsername(name));
    }


}