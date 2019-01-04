package com.clt.api.utils;

import com.clt.api.result.UserLoginVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class RedisExtendUtils {

    /**
     * 默认24小时后过期
     */
    @Value("${token.expire: 18000}")
    private int expire;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 登录token操作
     *
     * @param user 用户信息
     */
    @Async
    public void setLoginToken(UserLoginVO user, String token) {
        Map<String, Object> getToken = redisUtils.hmgetToken(String.valueOf(user.getId()));
        if (null != getToken && StringUtils.isNotBlank((String) getToken.get(Constants.KEY_NAME_TOKEN))) { // 删除用户上次登录的token
            redisUtils.deleteToken((String) getToken.get(Constants.KEY_NAME_TOKEN));
            redisUtils.deleteToken(String.valueOf(user.getId()));
        }
        Map<String, Object> map = new HashMap<>(Constants.INTEGER_VALUE_1);
        map.put(Constants.KEY_NAME_TOKEN, token);
        redisUtils.hmsetToken(String.valueOf(user.getId()), map);
        user.setExpireTime(DateUtils.addDay(new Date(), Constants.INTEGER_VALUE_1));
        redisUtils.setToken(token, user);
    }
}