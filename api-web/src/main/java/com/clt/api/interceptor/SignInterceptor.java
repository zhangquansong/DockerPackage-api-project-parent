package com.clt.api.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.clt.api.annotation.Sign;
import com.clt.api.exception.BizException;
import com.clt.api.exception.MyException;
import com.clt.api.filter.BodyReaderRequestWrapper;
import com.clt.api.utils.*;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * @ClassName : SignInterceptor
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:30
 * @Description :签名
 **/
@Slf4j
@Component
public class SignInterceptor extends HandlerInterceptorAdapter {

    private static final String SIGN_KEY = "sign"; // 请求参数中携带的签名信息

    private static final String TIMESTAMP_KEY = "timestamp";// 请求参数中携带的时间戳

    private static final String SPLIT_STR_THIRD = "$";

    @Value("${clt.signa.appkey}")
    String appKey;
    @Value("${clt.signa.appsecret}")
    String appSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Sign annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Sign.class);
        } else {
            return Boolean.TRUE;
        }
        if (!CheckUtils.isNotEmpty(annotation)) {
            return Boolean.TRUE;
        }
        String sign = request.getHeader(SIGN_KEY);//从header中获取sign
        if (StringUtils.isBlank(sign)) {
            throw new MyException(RestConstants.BIZ_SIGN_NULL_10006.getCode(), RestConstants.BIZ_SIGN_NULL_10006.getMessage());
        }
        String timeStamp = request.getHeader(TIMESTAMP_KEY);
        if (StringUtils.isBlank(timeStamp)) {
            throw new MyException(RestConstants.BIZ_TIMESTAMP_NULL_10007.getCode(), RestConstants.BIZ_TIMESTAMP_NULL_10007.getMessage());
        }
        if (this.isExpire(timeStamp)) {// 请求已过期
            log.info(RestConstants.REQUEST_EXPIRED_CODE_10009.getMessage());
            throw new MyException(RestConstants.REQUEST_EXPIRED_CODE_10009.getCode(), RestConstants.REQUEST_EXPIRED_CODE_10009.getMessage());
        }
        if (!this.checkSign(new BodyReaderRequestWrapper(request), sign, timeStamp)) { // 请求已失效
            log.info(RestConstants.REQUEST_INVALID_CORE_10008.getMessage());
            throw new MyException(RestConstants.REQUEST_INVALID_CORE_10008.getCode(), RestConstants.REQUEST_INVALID_CORE_10008.getMessage());
        }
        return Boolean.TRUE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }


    private boolean isExpire(String timestamp) {
        try {
            long dt = new Date().getTime();
            long lt = new Long(timestamp);
            long expire = new Date(lt).getTime();
            if ((dt - expire) / 60000 >= Constants.INTEGER_VALUE_1) {// 验证时间戳是否超过
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.info(RestConstants.REQUEST_PARAM_ERROR_CODE_10011.getMessage() + ":timestamp");
            throw new BizException(RestConstants.REQUEST_PARAM_ERROR_CODE_10011.getCode(), String.format("%s param[%s]", RestConstants.REQUEST_PARAM_ERROR_CODE_10011.getMessage(), "timestamp"));
        }
    }

    /**
     * 校验参数加时间戳，防止用户篡改请求
     *
     * @param request
     * @param sign
     * @param timeStamp
     * @return
     * @date 2018年5月29日 下午6:42:43
     * @author wangj@boruijinfu.com
     */
    private boolean checkSign(HttpServletRequest request, String sign, String timeStamp) {
        try {
            Map<String, Object> param = (CheckUtils.isNotEmpty(HttpHelper.getBodyString(request))) ? JSON.parseObject(HttpHelper.getBodyString(request)) : newHashMap();
            param.put(TIMESTAMP_KEY, timeStamp);
            List<String> values = newArrayList();
            param.keySet().stream().filter(signature -> !signature.equals(SIGN_KEY)).sorted().collect(Collectors.toList()).forEach(key -> values.add(isJSONString(String.valueOf(param.get(key)))));
            return sign.equalsIgnoreCase(signWithList(values));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * 生成原始的sign
     *
     * @param params
     * @return
     */
    private String signWithList(List<? extends Object> params) {
        StringBuffer sb = new StringBuffer(appKey);
        int size = params.size();
        for (int i = 0; i < size; i++) {
            sb.append(params.get(i));
        }
        sb.append(appSecret);
        return MD5Util.MD5Encode(sb.toString(), Charsets.UTF_8);
    }


    /**
     * 对复杂参数json进行排序
     *
     * @param text
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String isJSONString(String text) {
        try {
            Map<String, Object> map = JSON.parseObject(text, LinkedHashMap.class);
            Map<String, Object> finalMap = new LinkedHashMap<>();
            map.entrySet().stream().sorted(Map.Entry.<String, Object>comparingByKey()).forEachOrdered(lm -> finalMap.put(lm.getKey(), lm.getValue()));
            return JSON.toJSONString(finalMap);
        } catch (JSONException ex) {// 不是json则返回原串
            return text;
        }
    }
}
