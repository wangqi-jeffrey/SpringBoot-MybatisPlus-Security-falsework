package com.jeffrey.aspect;

import com.jeffrey.context.annotation.Cache;
import com.jeffrey.context.common.Cacheable;
import com.jeffrey.manager.CacheManager;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.StringUtil;
import com.jeffrey.utils.json.GsonUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Description: 缓存切面
 * 此切面会切加了{@link Cache}注解的方法
 * 根据{@link Cacheable}的key()方法指定的参数进行缓存。
 * 查询时优先读取缓存，缓存不存在时执行查询逻辑并缓存查询结果供下次查询使用。
 *
 * @author 滕国栋
 * @date 2020/1/6 0006 下午 16:28
 */
@Aspect
@Order(4)
@Component
public class CacheAspect {
    private static Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Autowired
    private CacheManager cacheManager;

    @Pointcut("@annotation(com.jeffrey.context.annotation.Cache)")
    public void cachePointcut() {

    }

    @Around("cachePointcut()")
    public Object processHandler(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = pjp.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(),
                methodSignature.getParameterTypes());
        Cache cache = currentMethod.getAnnotation(Cache.class);

        // 方法无参数或
        String key;
        Object[] args = pjp.getArgs();
        if (args.length == 1 && !(args[0] instanceof Cacheable)) {
            key = args[0].toString();
        } else if (args == null || args.length == 0 || !(args[0] instanceof Cacheable)) {
            return pjp.proceed();
        } else {
            Cacheable cacheable = (Cacheable) args[0];
            key = cacheable.key();
        }

        Object result = null;

        if (StringUtil.isBlank(key)) {
            return pjp.proceed();
        }

        String cacheKey = new StringBuilder().append(cache.prefix()).append(StringUtil.COLON).append(key)
                .append(StringUtil.COLON).append(cache.suffix()).toString();
        logger.info(LogUtil.getCommLog(String.format("【缓存数据】 - cacheKey=%s", cacheKey)));

        // 优先读取缓存
        String redisCacheKey = cacheManager.getKey(cacheKey);
        try {
            Object value = cacheManager.get(redisCacheKey);
            if (Objects.nonNull(value)) {
                Class type = cache.type();
                Class[] rawType = cache.rawType();
                CacheParameterizedType cacheParameterizedType = new CacheParameterizedType(type, rawType, type);
                result = GsonUtil.buildGson().fromJson(value.toString(), cacheParameterizedType);
            }
        } catch (Throwable e) {
            logger.error(LogUtil.getCommLog(String.format("查询缓存失败：%s", ExceptionUtils.getStackTrace(e))));
            // 缓存不穿透时，抛出异常
            if (!cache.isPenetrate()) {
                throw e;
            }
        }

        if (result != null) {
            return result;
        }

        result = pjp.proceed();

        // 缓存不存在时，保存缓存
        try {
            cacheManager.set(redisCacheKey, GsonUtil.toJson(result));
            if (cache.expire() != 0) {
                cacheManager.expire(redisCacheKey, cache.expire());
            }
        } catch (Throwable e) {
            logger.error(LogUtil.getCommLog(String.format("保存缓存失败：%s", ExceptionUtils.getStackTrace(e))));
        }

        return result;
    }

    /**
     * 缓存参数化数据类型
     */
    class CacheParameterizedType implements ParameterizedType {
        private final Class raw;
        private final Type[] args;
        private final Type owner;

        public CacheParameterizedType(Class raw, Class[] args, Type owner) {
            this.raw = raw;
            if (args == null) {
                this.args = new Type[0];
            } else {
                Type[] types = new Type[args.length];
                for (int i = 0; i < args.length; i++) {
                    types[i] = args[i];
                }
                this.args = types;
            }
            this.owner = owner;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return owner;
        }
    }
}
