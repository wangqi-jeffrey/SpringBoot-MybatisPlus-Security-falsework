package com.jeffrey.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 如果对象非空需要进行校验
 * ClassName: VerifyObject
 * date: 2018年11月9日
 *
 * @author YGC
 * @since JDK 1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyObject {

    /**
     * 描述信息
     *
     * @return
     */
    String message() default "";
}
