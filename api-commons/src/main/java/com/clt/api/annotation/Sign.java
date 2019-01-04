package com.clt.api.annotation;

import java.lang.annotation.*;

/**
 * 签名效验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sign {
}
