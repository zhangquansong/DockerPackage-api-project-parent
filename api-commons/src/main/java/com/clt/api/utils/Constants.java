package com.clt.api.utils;

/**
 * 系统常量类
 */
public class Constants {

    /**
     * 登录标识前缀
     */
    public static final String REDIS_API_PREFIX = "@@CLTUser";
    public static final String KEY_NAME_TOKEN = "token";
    public static final String KEY_NAME_SIGN = "sign";

    /**
     * 常用的0,1值
     */
    public static final Integer INTEGER_VALUE_0 = 0;
    public static final Integer INTEGER_VALUE_1 = 1;


    /**
     * 删除状态,0:未删除、1:删除
     */
    public static final Integer IS_DELETE_0 = 0;
    public static final Integer IS_DELETE_1 = 1;

    /**
     * 用户状态 0:启用、1:禁用
     */
    public static final Integer USER_STATUS_0 = 0;
    public static final Integer USER_STATUS_1 = 1;

}