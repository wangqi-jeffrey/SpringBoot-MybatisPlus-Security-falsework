package com.jeffrey.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 用户锁
 *
 * @author: 滕国栋
 * @date: 2019/4/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {
    /**
     * 锁定时间（秒）
     *
     * @return
     */
    long expire() default 10L;
}
