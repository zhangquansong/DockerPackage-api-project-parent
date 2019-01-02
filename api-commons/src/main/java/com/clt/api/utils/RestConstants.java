package com.clt.api.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RestConstants {
    SUCCESS(0, "成功"), ERROR(1, "失败"), OTHER(1000, "其他错误"), DEFAULT(10000, "默认错误");

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
        return Arrays.stream(RestConstants.values()).filter(a -> a.getCode().equals(code)).collect(Collectors.toList()).get(0);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
