package com.ledger.es_test1.filter;

import com.ledger.es_test1.service.UserService;
import com.ledger.es_test1.service.impl.UserServiceImpl;
import com.ledger.es_test1.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            String token = header.replace("Bearer" + " ", "");
            //是否过期或者有效
            if (JwtUtil.validateJwt(token, "ledger")) {
                String userNameFromToken = JwtUtil.getUserNameFromToken(token, "ledger");
                if (userNameFromToken != null) {
                    UserDetails userDetails = new UserServiceImpl().loadUserByUsername(userNameFromToken);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                    response.setHeader("Authorization", "Bearer" + " " + JwtUtil.refreshToken(token, "ledger"));
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
