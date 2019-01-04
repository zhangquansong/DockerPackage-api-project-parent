package com.clt.api.interceptor;


import com.clt.api.annotation.Login;
import com.clt.api.entity.User;
import com.clt.api.exception.MyException;
import com.clt.api.result.UserLoginVO;
import com.clt.api.service.UserService;
import com.clt.api.utils.CheckUtils;
import com.clt.api.utils.Constants;
import com.clt.api.utils.RedisUtils;
import com.clt.api.utils.RestConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * 权限(Token)验证(处理通用的Http Header)
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }
        if (!CheckUtils.isNotEmpty(annotation)) {
            return true;
        }
        String token = request.getHeader(Constants.KEY_NAME_TOKEN);//从header中获取token
        if (StringUtils.isBlank(token)) {
            //token为空
            throw new MyException(RestConstants.BIZ_TOKEN_NULL_10002.getCode(), RestConstants.BIZ_TOKEN_NULL_10002.getMessage());
        }
        UserLoginVO redisUser = redisUtils.getToken(token, UserLoginVO.class);//查询redis中token对应的用户信息
        if (!CheckUtils.isNotEmpty(redisUser)
                || (CheckUtils.isNotEmpty(redisUser) && redisUser.getExpireTime().getTime() < System.currentTimeMillis())) {
            //token失效
            throw new MyException(RestConstants.BIZ_TOKEN_EFFECT_10003.getCode(), RestConstants.BIZ_TOKEN_EFFECT_10003.getMessage());
        }
        Map<String, Object> map = redisUtils.hmgetToken(String.valueOf(redisUser.getId()));//查询redis中token信息
        if (!CheckUtils.isNotEmpty(map)
                || !StringUtils.equalsIgnoreCase((String) map.get(Constants.KEY_NAME_TOKEN), redisUser.getToken())) {
            //token异常
            throw new MyException(RestConstants.BIZ_TOKEN_EXCEPTION_10004.getCode(), RestConstants.BIZ_TOKEN_EXCEPTION_10004.getMessage());
        }

        User user = userService.findUserByUserId(redisUser.getId());//查询数据库用户信息
        if (!CheckUtils.isNotEmpty(user)) {
            //用户不存在
            throw new MyException(RestConstants.BIZ_USER_NULL_10005.getCode(), RestConstants.BIZ_USER_NULL_10005.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}