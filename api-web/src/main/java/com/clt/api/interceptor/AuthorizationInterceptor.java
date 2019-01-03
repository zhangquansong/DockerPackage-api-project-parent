package com.clt.api.interceptor;


import com.clt.api.annotation.Login;
import com.clt.api.exception.AuthenticationFailureException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证(处理通用的Http Header)
 *
 * @author wangj@boruijinfu.com
 * @date 2018年4月23日 上午10:50:18
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }
        if (annotation == null) {
            return true;
        }
        //从header中获取token
        String token = request.getHeader("token");
        //token为空
        if (StringUtils.isBlank(token)) {
            throw new AuthenticationFailureException("token不能为空");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
