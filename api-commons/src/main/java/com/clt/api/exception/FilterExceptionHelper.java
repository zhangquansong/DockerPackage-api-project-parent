package com.clt.api.exception;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter异常处理
 * @date 2018年6月5日 下午9:01:14
 * @author wangj@boruijinfu.com
 */
public class FilterExceptionHelper {

    /**
     * 处理BizException错误
     * 
     * @param bz
     * @param request
     * @param response
     * @throws IOException
     */
    public static void handleBizException(BizException bz, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        MyExceptionHandler.sendErrorMessage(bz.getMessage(), request, response);
    }

    /**
     * 处理BizException错误
     * 
     * @param bz
     * @param request
     * @param response
     * @throws IOException
     */
    public static void handleBizException(BizException bz, int httpStatus, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(httpStatus);
        MyExceptionHandler.sendErrorMessage(bz.getMessage(), request, response);
    }

}
