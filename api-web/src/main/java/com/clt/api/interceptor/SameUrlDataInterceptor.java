package com.clt.api.interceptor;

import com.clt.api.annotation.SameUrlData;
import com.clt.api.exception.MyException;
import com.clt.api.utils.Constants;
import com.clt.api.utils.RestConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : SameUrlDataInterceptor
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:29
 * @Description :一个用户 相同url 同时提交 相同数据 验证 主要通过 session中保存到的url 和 请求参数。如果和上次相同，则是重复提交表单
 **/
@Component
public class SameUrlDataInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SameUrlData annotation = method.getAnnotation(SameUrlData.class);
            if (annotation != null) {
                if (repeatDataValidator(request)) {// 如果重复相同数据
                    throw new MyException(RestConstants.SAMEURLDATA_REPEAT_10012.getCode(), RestConstants.SAMEURLDATA_REPEAT_10012.getMessage());
                } else {
                    return true;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证同一个url数据是否相同提交 ,相同返回true
     *
     * @param httpServletRequest
     * @return
     */
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest) throws Exception {
        String url = httpServletRequest.getRequestURI();
        Map<String, String> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
        String str = "";
        String params = "";
        while ((str = reader.readLine()) != null) {//一行一行的读取body体里面的内容；
            params += str;
        }
        map.put(url, params);
        String nowUrlParams = map.toString();
        Object preUrlParams = httpServletRequest.getSession().getAttribute(Constants.REPEATDATA);
        if (preUrlParams == null) {// 如果上一个数据为null,表示还没有访问页面
            httpServletRequest.getSession().setAttribute(Constants.REPEATDATA, nowUrlParams);
            return false;
        } else {// 否则，已经访问过页面
            if (preUrlParams.toString().equals(nowUrlParams)) {// 如果上次url+数据和本次url+数据相同，则表示重复提交数据
                return true;
            } else {// 如果上次 url+数据 和本次url加数据不同，则不是重复提交
                httpServletRequest.getSession().setAttribute(Constants.REPEATDATA, nowUrlParams);
                return false;
            }
        }
    }

}