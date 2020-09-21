package com.jeffrey.utils;


import com.jeffrey.context.enums.EnumInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description: 枚举工具类
 *
 * @author: 滕国栋
 * @date: 2019/10/16 0016 下午 13:34
 */
public class EnumUtil {

    /**
     * 根据枚举类和枚举值获取枚举对象
     *
     * @param c
     * @param value
     * @param <E>
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <E extends EnumInterface, T> E getByValue(Class<E> c, T value) {
        try {
            Method values = c.getMethod("values");
            E[] enums = (E[]) values.invoke(null, null);
            for (E e : enums) {
                if (e.getValue().equals(value)) {
                    return e;
                }
            }
        } catch (Throwable e) {
            return null;
        }

        return null;
    }
}
