package com.clt.api.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.clt.api.interceptor.AuthorizationInterceptor;
import com.clt.api.interceptor.SameUrlDataInterceptor;
import com.clt.api.interceptor.SignInterceptor;
import com.clt.api.resolver.LoginUserHandlerMethodArgumentResolver;
import com.clt.api.security.SensitivePropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : WebMvcConfig
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:27
 * @Description : MVC配置 WebMvcConfigurationSupport 与 WebMvcConfigurerAdapter
 * 都可以配置MVC,WebMvcConfigurationSupport 支持的自定义的配置更多更全
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private SignInterceptor signInterceptor;

    @Autowired
    private SameUrlDataInterceptor sameUrlDataInterceptor;

    @Autowired
    private Environment env;

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (Boolean.valueOf(env.getProperty("spring.swagger.enable"))) { // 开发环境打开api接口文档
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").addResourceLocations("classpath:/public/");
            registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
            super.addResourceHandlers(registry);
        }
    }

    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 对/api/路径下所有@Login注解，增加登录认证 处理通用的Http Header
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
        registry.addInterceptor(signInterceptor).addPathPatterns("/**");
        registry.addInterceptor(sameUrlDataInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 注入当前登录用户
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }

    /**
     * 不对url地址.后进行过滤
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }

    /**
     * 扩展原版的HandlerMapping，实现版本号匹配规则
     */
//    @Override
//    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
//        MultiVersionRequestMappingHandlerMapping handlerMapping = new MultiVersionRequestMappingHandlerMapping();
//        handlerMapping.setOrder(0);
//        handlerMapping.setInterceptors(getInterceptors());
//        handlerMapping.registerApiVersionCodeDiscoverer(new DefaultApiVersionCodeDiscoverer());
//        return handlerMapping;
//    }

    /**
     * 跨域请求支持
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS").allowedHeaders("*")
                        .allowCredentials(true)
                        .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);
            }
        };
    }


    /**
     * FastJson转换器（处理IOS数值类型Double精度.9999999999999999的问题）
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        // 1.需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        // 处理IOS数值类型Double精度.9999999999999999的问题
        SerializeConfig config = SerializeConfig.getGlobalInstance();
        //config.put(Double.class, new DoubleValueSerializer());
        // 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializeFilters(new SensitivePropertyFilter());// SensitivePropertyFilter增加字段脱敏
        fastJsonConfig.setSerializeConfig(config);
        // BrowserCompatible 将中文都会序列化为uXXXX格式，字节数会多一些，但是能兼容IE 6，默认为false
        // QuoteFieldNames———-输出key时是否使用双引号,默认为true
        // WriteMapNullValue——–是否输出值为null的字段,默认为false
        // WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
        // WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
        // WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
        // WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
        // DisableCircularReferenceDetect 对象转化成json避免$ref：
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullNumberAsZero, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteEnumUsingToString, SerializerFeature.DisableCircularReferenceDetect);
        // 3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4、将convert添加到converters当中.
        converters.add(fastConverter);
    }
}