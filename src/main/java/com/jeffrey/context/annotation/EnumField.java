package com.jeffrey.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 枚举值
 * ClassName: EnumField
 * date: 2018年11月9日
 *
 * @author YGC
 * @since JDK 1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumField {

    /**
     * 数据范围
     *
     * @return
     */
    String range();
}
