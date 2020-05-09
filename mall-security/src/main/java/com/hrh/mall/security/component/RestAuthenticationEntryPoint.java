package com.hrh.mall.security.component;

import cn.hutool.json.JSONUtil;
import com.hrh.mall.dto.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.security.component
 * @ClassName: RestAuthenticationEntryPoint
 * @Author: HuRonghua
 * @Description: 自定定义token过期或者未登录返回结果
 * @Date: 2020/4/27 14:35
 * @Version: 1.0
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSONUtil.parse(new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"未登录或者token已过期")));
        httpServletResponse.getWriter().flush();
    }
}
