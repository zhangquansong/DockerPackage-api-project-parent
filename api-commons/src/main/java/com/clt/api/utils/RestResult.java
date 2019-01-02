package com.clt.api.utils;

import java.io.Serializable;

/**
 * 接口返回包装类
 *
 * @param <T>
 */
public class RestResult<T> implements Serializable {

    private Integer code;//状态码
    private String message;//返回信息
    private T data;//返回数据

    public RestResult() {
    }

    public RestResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> RestResult<T> response(Integer code, T data) {
        return new RestResult(code, RestConstants.getMessageByCode(code).getMessage(), data);
    }

    public static <T> RestResult<T> successResponse() {
        return new RestResult(RestConstants.SUCCESS.getCode(),
                RestConstants.getMessageByCode(RestConstants.SUCCESS.getCode()).getMessage());
    }

    public static <T> RestResult<T> successResponse(T data) {
        return new RestResult(RestConstants.SUCCESS.getCode(),
                RestConstants.getMessageByCode(RestConstants.SUCCESS.getCode()).getMessage(), data);
    }

    public static <T> RestResult<T> errorResponse() {
        return new RestResult(RestConstants.ERROR.getCode(),
                RestConstants.getMessageByCode(RestConstants.ERROR.getCode()).getMessage());
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
