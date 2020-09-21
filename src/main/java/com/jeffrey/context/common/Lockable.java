package com.jeffrey.context.common;

import com.jeffrey.aspect.LockAspect;
import com.jeffrey.context.annotation.Lock;

import java.util.List;

/**
 * Description: 结合{@link LockAspect}一起使用。
 * 加了{@link Lock}注解的方法的参数
 * 需实现该接口（若多个入参都实现了该接口，取第一个），并重写lockKey()。那么LockAspect
 * 会根据lockKeys()依次加分布式锁。
 *
 * @author: 滕国栋
 * @date: 2019/4/16
 */
public interface Lockable {
    List<String> lockKeys();
}
