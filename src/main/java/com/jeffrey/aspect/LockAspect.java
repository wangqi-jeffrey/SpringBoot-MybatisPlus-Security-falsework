package com.jeffrey.aspect;

import com.google.common.collect.Lists;
import com.jeffrey.context.annotation.Lock;
import com.jeffrey.context.common.Lockable;
import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.LockException;
import com.jeffrey.manager.LockManager;
import com.jeffrey.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Description: 用户锁通用处理切面
 * 此切面会切加了{@link Lock}注解的方法
 * 根据{@link Lockable}的lockKeys()方法指定的参数进行锁定。
 * 如果参数未实现{@link Lockable}接口，将使用方法第一个参数的toString作为lockKey
 *
 * @author: 滕国栋
 * @date: 2019/4/15
 */
@Slf4j
@Aspect
@Component
public class LockAspect implements Ordered {

    @Autowired
    private LockManager lockManager;

    @Pointcut("@annotation(com.jeffrey.context.annotation.Lock)")
    public void lockPointcut() {

    }

    @Around("lockPointcut()")
    public Object processHandler(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = pjp.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(),
                methodSignature.getParameterTypes());
        Lock lock = currentMethod.getAnnotation(Lock.class);

        Object[] args = pjp.getArgs();
        if (args == null || args.length == 0) {
            return pjp.proceed();
        }

        Object result = null;
        List<String> lockKeys = Lists.newArrayList();
        if (args[0] instanceof Lockable) {
            Lockable lockable = (Lockable) args[0];
            lockKeys = lockable.lockKeys();
        } else {
            lockKeys.add(args[0].toString());
        }

        if (CollectionUtils.isEmpty(lockKeys)) {
            return pjp.proceed();
        }

        log.info(LogUtil.getCommLog(String.format("【锁定用户操作】 - lockKeys=%s", lockKeys)));

        // 正序加锁，倒叙解锁
        try {
            for (int i = 0; i < lockKeys.size(); i++) {
                if (!lockManager.lock(lockKeys.get(i), lock.expire())) {
                    log.error(LogUtil.getCommLog(String.format("锁定(%s)失败", lockKeys.get(i))));
                    throw new LockException(ErrorCodeEnum._10013);
                }
            }

            result = pjp.proceed();
        } catch (LockException e) {
            log.error(LogUtil.getCommLog(
                    String.format("【锁定用户操作失败】- param=%s,失败原因：%s", args[0], ExceptionUtils.getStackTrace(e))));
            throw e;
        } catch (Throwable e) {
            log.error(LogUtil.getCommLog(ExceptionUtils.getStackTrace(e)));
            throw e;
        } finally {
            for (int i = lockKeys.size() - 1; i >= 0; i--) {
                lockManager.unLock(lockKeys.get(i));
            }

            log.info(LogUtil.getCommLog(String.format("【解锁用户操作】 - lockKeys=%s", lockKeys)));
        }

        return result;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
