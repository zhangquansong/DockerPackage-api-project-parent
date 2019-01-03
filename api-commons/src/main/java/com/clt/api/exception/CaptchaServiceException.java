package com.clt.api.exception;

public class CaptchaServiceException extends RuntimeException {
    
    private static final long serialVersionUID = 2864817652714842988L;
    
    private Throwable cause;

    public CaptchaServiceException(String message) {
        super(message);
    }

    public CaptchaServiceException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public CaptchaServiceException(Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}