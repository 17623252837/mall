package com.hrh.mall.security.component;

import cn.hutool.json.JSONUtil;
import com.hrh.mall.dto.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 自定义权限返回结果
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.security.component
 * @ClassName: RestfulAccessDeniedHandler
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/4/27 14:32
 * @Version: 1.0
 */

public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSONUtil.parse(new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"权限不足")));
        httpServletResponse.getWriter().flush();
    }
}
