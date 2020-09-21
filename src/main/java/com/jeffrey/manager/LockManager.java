package com.jeffrey.manager;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.LockException;
import com.jeffrey.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 锁管理
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:51
 */
@Slf4j
@Service
public class LockManager {
    private static final int DELTA = 1;

    private static final String LOCK_KEY = "LOCK:";

    @Autowired
    private CacheManager cacheManager;

    /**
     * 锁定
     *
     * @param key
     * @return
     * @throws Exception
     */
    public boolean lock(String key, long timeout) throws Exception {
        String lockKey = cacheManager.getKey(LOCK_KEY + key);

        long lockFlag = cacheManager.incr(lockKey, DELTA);
        if (lockFlag == DELTA) {
            cacheManager.expire(lockKey, timeout);
        }
        log.info(LogUtil.getCommLog(String.format("调用Redis实现锁返回值:%s", lockFlag)));
        return lockFlag == DELTA;
    }

    /**
     * 解锁
     *
     * @param key
     */
    public void unLock(String key) {
        try {
            String lockKey = cacheManager.getKey(LOCK_KEY + key);
            cacheManager.del(lockKey);
            log.error(LogUtil.getCommLog(String.format("释放锁(%s)成功", key)));
        } catch (Exception e) {
            log.error(LogUtil.getCommLog(String.format("释放锁(%s)失败:%s", key, ExceptionUtils.getStackTrace(e))));
            throw new LockException(ErrorCodeEnum._10014);
        }
    }
}
