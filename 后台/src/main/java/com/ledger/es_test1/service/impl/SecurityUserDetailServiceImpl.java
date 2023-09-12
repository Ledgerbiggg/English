package com.ledger.es_test1.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SecurityUserDetailServiceImpl implements UserDetailsService {
//    @Resource
//    private SysUserService sysUserService;
//    @Resource
//    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// TODO
//        SysUser sysUser = sysUserService.getUserByName(username);
//        if(sysUser==null){
//            throw new UsernameNotFoundException("用户名没有找到");
//        }
//        //根据用户id获取用户的权限
//        List<String> auths = sysMenuService.queryPermissionByUserId(sysUser.getUserId());
//        SecurityUser securityUser = new SecurityUser(sysUser);
//        List<SimpleGrantedAuthority> collect = auths
//                .stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        securityUser.setAuthorities(collect);
        return null;
    }
}