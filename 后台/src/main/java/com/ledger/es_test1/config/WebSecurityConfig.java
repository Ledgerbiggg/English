package com.ledger.es_test1.config;

import com.ledger.es_test1.handler.AppAccessDeniedHandler;
import com.ledger.es_test1.handler.AppAuthenticationFailureHandler;
import com.ledger.es_test1.handler.AppAuthenticationSuccessHandler;
import com.ledger.es_test1.handler.AppLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

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

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/login");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                // 配置HTTP请求的权限控制规则开始
                // 任何请求都需要经过身份验证（用户需要登录才能访问）
                .anyRequest().authenticated()
                // 配置HTTP请求的权限控制规则结束
                .and()
                .formLogin() // 配置表单登录
                // 禁用表单登录功能，适用于不需要传统表单登录的应用程序
                .disable()
                .csrf() // 配置CSRF（跨站请求伪造）防护
                // 禁用CSRF防护，适用于API或前后端分离应用程序
                .disable();

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