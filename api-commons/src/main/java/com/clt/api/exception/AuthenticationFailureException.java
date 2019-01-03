package com.clt.api.exception;

/***
 * 登录失效异常:401
 */
public class AuthenticationFailureException extends RuntimeException {

	private static final long serialVersionUID = 4745836273751186869L;
	private String msg;
	private int code = 401;

	public AuthenticationFailureException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public AuthenticationFailureException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
