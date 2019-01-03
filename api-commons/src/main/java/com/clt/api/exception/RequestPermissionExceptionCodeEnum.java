package com.clt.api.exception;

/**
 * uri 签名检查异常枚举
 * @author chenbin
 */
public enum RequestPermissionExceptionCodeEnum {
	
	REQUEST_INVALID_CORE("签名检查异常:非法请求",4100),
	REQUEST_EXPIRED_CODE("签名检查异常:请求已过期,请检查系统时间", 4101),
	REQUEST_SIGN_ERROR_CODE("签名检查异常:签名错误", 4102),
	REQUEST_PARAM_ERROR_CODE("签名检查异常:参数错误",4103);
	
	private int code;
	private String msg;
	
	private  RequestPermissionExceptionCodeEnum(String msg,int code) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
