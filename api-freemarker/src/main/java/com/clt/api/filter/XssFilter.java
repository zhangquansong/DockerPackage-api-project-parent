package com.clt.api.filter;

import com.clt.api.helper.InitHeaderContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName : XssFilter
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 4:14
 * @Description :XSS过滤
 **/
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 初始化header
        InitHeaderContext.init(request, response);
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
    }

}