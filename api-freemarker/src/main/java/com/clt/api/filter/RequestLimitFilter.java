package com.clt.api.filter;

import com.alibaba.fastjson.JSON;
import com.clt.api.entity.BlackUser;
import com.clt.api.entity.User;
import com.clt.api.service.BlackUserExtendService;
import com.clt.api.utils.*;
import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : RequestLimitFilter
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:41
 * @Description :全局ip频繁访问黑名单(需要开启Redis支持)
 **/
public class RequestLimitFilter implements Filter {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BlackUserExtendService blackUserExtendService;

    @Value("${clt.glob.limitTime}")
    private long globLimitTime;

    @Value("${clt.glob.limitCount}")
    private int globLimitCount;

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        // ip频繁访问黑名单(需要开启Redis支持)
        String token = RequestHeaderContext.getInstance().token;
        User e = StringUtils.isNotBlank(token) ? redisUtils.getToken(token, User.class) : null;
        String mobile = CheckUtils.isNotEmpty(e) ? e.getUserPhone() : null;
        String ip = IPUtils.getIpAddr(request);
        String imei = RequestHeaderContext.getInstance().deviceId;
        String url = request.getRequestURI();
        String key = Constants.GLOB_BLACK_LIST.concat(Strings.nullToEmpty(mobile)).concat(Strings.nullToEmpty(imei)).concat(ip);
        boolean flag = CheckUtils.isNotEmpty(redisUtils.get(key));
        if (flag) {
            outprintJSON(response);
            return;
        } else {
            if (StringUtils.isNotBlank(mobile)) {
                // 判断Redis中是否已经是黑名单 
                String concatKey = Constants.SMS_BLACK_LIST.concat(mobile);
                Object o = redisUtils.get(concatKey);
                if (CheckUtils.isNotEmpty(o)) {
                    outprintJSON(response);
                    return;
                }
            }
            boolean isBlacklist = isBlacklist(request, e, url, ip, imei, key, globLimitTime, globLimitCount);
            if (isBlacklist) {
                outprintJSON(response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 频繁访问永久加入黑名单
     *
     * @param request
     * @param user
     * @param ip
     * @param imei
     * @param limitTime
     * @param limitCount
     * @return
     * @date 2018年5月14日 下午8:36:19
     * @author wangj@boruijinfu.com
     */
    public boolean isBlacklist(HttpServletRequest request, User user, String url, String ip, String imei, String key, long limitTime, int limitCount) {
        Long count = redisTemplate.opsForValue().increment(key, Constants.INIT_INCREMENT_VALUE);
        if (CheckUtils.isNotEmpty(count) && count.equals(Constants.INIT_INCREMENT_VALUE)) {
            redisTemplate.expire(key, limitTime, TimeUnit.SECONDS);
        }
        if (count > limitCount) {
            RestResult<BlackUser> restResult = new RestResult<>();
            if (CheckUtils.isNotEmpty(user)) {
                StringBuffer description = new StringBuffer();
                description.append("该用户频繁请求 : ").append(url).append(" 这个接口").append(count).append("次！将其加入黑名单！");
                restResult = blackUserExtendService.addBlackUserWithRedis(user, description.toString(), ip, imei);
            }
            redisUtils.set(key, JSON.toJSONString(JSON.toJSONString(restResult.getData())), -1);
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @param response
     * @return void
     * @Author zhangquansong
     * @Date 2019/1/5 0005 下午 4:49
     * @Description :  json错误信息
     **/
    private void outprintJSON(ServletResponse response) throws UnsupportedEncodingException, IOException {
        PrintWriter writer = null;
        OutputStreamWriter osw = null;
        osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
        writer = new PrintWriter(osw, Boolean.TRUE);
        writer.write(JSON.toJSONString(RestResult.errorResponse(RestConstants.BLACK_USER_10013.getCode(),
                RestConstants.BLACK_USER_10013.getMessage())));
        writer.flush();
        writer.close();
        osw.close();
        return;
    }
}
