package com.clt.api.exception;

/**
 * 业务异常
 * 
 * @author Administrator
 *
 */
public class BizException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -298311232780808293L;

    private int code = 400;

    private String message;

    public BizException() {
        super();
    }

    public BizException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
