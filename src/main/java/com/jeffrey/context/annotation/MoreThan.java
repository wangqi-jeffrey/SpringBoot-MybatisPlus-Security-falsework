package com.jeffrey.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 比某个属性大
 * ClassName: MoreThan
 * date: 2018年11月9日
 *
 * @author YGC
 * @since JDK 1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MoreThan {

    /**
     * 比较对象
     *
     * @return
     */
    String target();

    /**
     * 描述信息
     *
     * @return
     */
    String message();

    /**
     * 目标描述信息
     *
     * @return
     */
    String targetMessage();
}
