package com.jeffrey.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 缓存
 *
 * @author 滕国栋
 * @date 2020/1/6 0006 下午 16:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * 前缀
     *
     * @return
     */
    String prefix() default "";

    /**
     * 后缀
     *
     * @return
     */
    String suffix() default "";

    /**
     * 缓存生效时间
     * 默认10s
     *
     * @return
     */
    long expire() default 10L;

    /**
     * 缓存读取失败时，是否穿透缓存，默认：是
     *
     * @return
     */
    boolean isPenetrate() default true;

    /**
     * 缓存的数据类型
     *
     * @return
     */
    Class type();

    /**
     * 缓存的元数据类型
     * 例如O<T>时，代表T类型
     *
     * @return
     */
    Class[] rawType() default {};
}
