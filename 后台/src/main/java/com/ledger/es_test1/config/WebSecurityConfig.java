package com.ledger.es_test1.config;

import com.ledger.es_test1.filter.JwtAuthenticationTokenFilter;
import com.ledger.es_test1.filter.ValidateCodeFilter;
import com.ledger.es_test1.handler.AppAccessDeniedHandler;
import com.ledger.es_test1.handler.AppAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    private AppAccessDeniedHandler appAccessDeniedHandler;
    @Resource
    private AppAuthenticationFailureHandler appAuthenticationFailureHandler;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .addFilterBefore(validateCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                // 任何请求都需要经过身份验证（用户需要登录才能访问）
                .anyRequest()
                .authenticated()
                // 配置HTTP请求的权限控制规则结束
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .and()
                //不使用缓存
                .headers()
                .cacheControl();

        httpSecurity.formLogin()
                //登录失败的处理器
                .failureHandler(appAuthenticationFailureHandler);
        //访问权限不够的处理器
        httpSecurity.exceptionHandling().accessDeniedHandler(appAccessDeniedHandler);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/captcha","/loginUser");
    }

    @Bean
    public ValidateCodeFilter validateCodeFilter() {
        return new ValidateCodeFilter();
    }
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }


}