package com.jeffrey.context.common;

/**
 * Description: 结合{@link com.huizhaofang.hhz.aspect.CacheAspect}一起使用。
 * 加了{@link com.huizhaofang.hhz.context.annotation.Cache}的方法的第一个（注意是第一个）参数，
 * 需要实现key()方法，指定缓存标识。
 *
 * @author 滕国栋
 * @date 2020/1/6 0006 下午 16:23
 */
public interface Cacheable {
    String key();
}
