package com.clt.api.exception;

/**
 * HTTP请求超出设定的限制异常
 * @date 2018年5月12日 下午2:55:19
 * @author wangj@boruijinfu.com
 */
public class RequestLimitException extends MyException {
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }

    public RequestLimitException(String message, Throwable cause){
        super(message, cause);
    }
}