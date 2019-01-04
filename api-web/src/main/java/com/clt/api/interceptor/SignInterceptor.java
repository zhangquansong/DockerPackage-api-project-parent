package com.clt.api.interceptor;


import com.clt.api.annotation.Sign;
import com.clt.api.exception.MyException;
import com.clt.api.utils.CheckUtils;
import com.clt.api.utils.Constants;
import com.clt.api.utils.RestConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 签名
 */
@Component
public class SignInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Sign annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Sign.class);
        } else {
            return true;
        }
        if (!CheckUtils.isNotEmpty(annotation)) {
            return true;
        }
        String sign = request.getHeader(Constants.KEY_NAME_SIGN);//从header中获取sign
        if (StringUtils.isBlank(sign)) {
            throw new MyException(RestConstants.BIZ_SIGN_NULL_10006.getCode(), RestConstants.BIZ_SIGN_NULL_10006.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
