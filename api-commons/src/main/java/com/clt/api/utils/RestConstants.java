package com.clt.api.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RestConstants {
    /**
     * 系统提示
     */
    SUCCESS(Constants.INTEGER_VALUE_0, "成功"), ERROR(Constants.INTEGER_VALUE_1, "失败"),
    OTHER(1000, "其他错误"), DEFAULT(10000, "默认错误"),

    /**
     * 业务提示(10001~90000)
     */
    BIZ_USER_LOGIN_10001(10001, "用户名或者密码错误"), BIZ_TOKEN_NULL_10002(10002, "token为空"),
    BIZ_TOKEN_EFFECT_10003(10003, "token失效"), BIZ_TOKEN_EXCEPTION_10004(10004, "token异常"),
    BIZ_USER_NULL_10005(10005, "用户不存在"), BIZ_SIGN_NULL_10006(10006, "sign为空");

    private final Integer code;//状态吗
    private final String message;//信息

    RestConstants(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static RestConstants getMessageByCode(Integer code) {
        if (null == code) {
            return DEFAULT;
        }
        return Arrays.stream(RestConstants.values()).filter(a -> a.getCode().equals(code))
                .collect(Collectors.toList()).get(Constants.INTEGER_VALUE_0);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
