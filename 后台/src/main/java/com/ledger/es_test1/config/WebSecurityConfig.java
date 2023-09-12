package com.ledger.es_test1.config;

import com.ledger.es_test1.filter.ValidateCodeFilter;
import com.ledger.es_test1.handler.AppAccessDeniedHandler;
import com.ledger.es_test1.handler.AppAuthenticationFailureHandler;
import com.ledger.es_test1.handler.AppAuthenticationSuccessHandler;
import com.ledger.es_test1.handler.AppLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
    @Resource
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
    @Resource
    private AppAccessDeniedHandler appAccessDeniedHandler;
    @Resource
    private AppAuthenticationFailureHandler appAuthenticationFailureHandler;
    @Resource
    private AppLogoutSuccessHandler appLogoutSuccessHandler;
    @Resource
    private ValidateCodeFilter validateCodeFilter;


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/login");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                // 配置HTTP请求的权限控制规则开始
                .antMatchers("/captcha")
                .permitAll()
                // 任何请求都需要经过身份验证（用户需要登录才能访问）
                .anyRequest()
                .authenticated()
                // 配置HTTP请求的权限控制规则结束
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable();

        httpSecurity.formLogin()
                //成功登录的处理器
                .successHandler(appAuthenticationSuccessHandler)
                //登录失败的处理器
                .failureHandler(appAuthenticationFailureHandler);
        //退出成功的处理器
        httpSecurity.logout().logoutSuccessHandler(appLogoutSuccessHandler);
        //访问权限不够的处理器
        httpSecurity.exceptionHandling().accessDeniedHandler(appAccessDeniedHandler);
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
//    /**
//     * 针对url进行拦截
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest() //任何请求
//                .authenticated(); //没有配置
//        http.formLogin()
//                //成功登录的处理器
//                .successHandler(appAuthenticationSuccessHandler)
//                //登录失败的处理器
//                .failureHandler(appAuthenticationFailureHandler)
//                .permitAll(); //允许表单登录
//        //退出成功的处理器
//        http.logout().logoutSuccessHandler(appLogoutSuccessHandler);
//        //访问权限不够的处理器
//        http.exceptionHandling().accessDeniedHandler(appAccessDeniedHandler);
//    }


}