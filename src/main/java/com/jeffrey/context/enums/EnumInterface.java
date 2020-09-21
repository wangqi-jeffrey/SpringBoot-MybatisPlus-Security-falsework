package com.jeffrey.context.enums;

/**
 * Description: 枚举接口
 * 推荐所有枚举类实现该接口，并重写getValue()方法。
 * 可以使用EnumUtil.getByValue()方法根据枚举值获对应的枚举对象，详情请参考EnumUtil.getByValue()的方法说明。
 *
 * @author: 滕国栋
 * @date: 2019/10/16 0016 下午 13:53
 */
public interface EnumInterface<T> {

    /**
     * 获得value
     *
     * @return
     */
    T getValue();
}
