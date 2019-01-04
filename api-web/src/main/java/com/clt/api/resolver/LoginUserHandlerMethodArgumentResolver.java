package com.clt.api.resolver;

import com.clt.api.annotation.LoginUser;
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
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request, WebDataBinderFactory factory)
            throws MyException {
        String token = request.getHeader(Constants.KEY_NAME_TOKEN);//获取header中的token
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
        return user;
    }
}
