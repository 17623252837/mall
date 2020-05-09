package com.hrh.mall.security.configure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.security.configure
 * @ClassName: TokenFilter
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/5/5 20:30
 * @Version: 1.0
 */
public class TokenFilter extends OncePerRequestFilter {

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       // 获取请求头
        String access_token = httpServletRequest.getParameter("access_token");
        System.out.println(access_token);

        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        Principal user ,Principal = httpServletRequest.getUserPrincipal();
        while(parameterNames.hasMoreElements()){
            System.out.println();
        }

        //  UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
