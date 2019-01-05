package com.clt.api.config;

import com.clt.api.filter.RequestLimitFilter;
import com.clt.api.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

/**
 * @ClassName : FilterConfig
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:48
 * @Description :Filter配置
 **/
@Configuration
public class FilterConfig {


    /**
     * XSS过滤防止sql注入攻击
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * ip频繁访问黑名单(需要开启Redis支持)
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean requestLimitFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(requestLimitFilter());
        registration.addUrlPatterns("/*");
        registration.setName("requestLimitFilter");
        registration.setOrder(2);
        return registration;
    }

    /**
     * 将Filter交给spring管理，如果使用new XXXXFilter()不能注入springbean
     *
     * @return
     * @date 2018年5月13日 下午12:06:06
     * @author wangj@boruijinfu.com
     */
    @Bean
    public Filter requestLimitFilter() {
        return new RequestLimitFilter();
    }

}
