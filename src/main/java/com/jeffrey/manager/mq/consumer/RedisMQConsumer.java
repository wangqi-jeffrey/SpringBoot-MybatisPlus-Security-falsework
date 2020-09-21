package com.jeffrey.manager.mq.consumer;

import com.jeffrey.context.configuration.MQConfiguration;
import com.jeffrey.manager.CacheManager;
import com.jeffrey.manager.LockManager;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: Redis消息消费者
 *
 * @author TGD
 * @date 2020/08/26 下午 16:44
 */
@Slf4j
@Component
public class RedisMQConsumer implements MQConsumer {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private LockManager lockManager;

    /**
     * 接收队列消息
     *
     * @param queue
     * @return
     */
    @Override
    public String receiveMsg(String queue) {
        String key = getKey(queue);
        int index = 0;
        try {
            String content = (String) cacheManager.lGetIndex(key, index);
            if (StringUtil.isBlank(content)) {
                return null;
            }
            if (!lockManager.lock(queue, 600L)) {
                // log.error(LogUtil.getCommLog(String.format("锁定(%s)失败", queue)));
                return null;
            }

            log.info(LogUtil.getCommLog(String.format("消费消息，队列：%s，内容：%s", queue, content)));
            return content;
        } catch (Throwable e) {
            log.error(LogUtil.getCommLog(ExceptionUtils.getStackTrace(e)));
        }
        return null;
    }

    /**
     * 删除队列消息
     *
     * @param queue
     */
    @Override
    public void deleteMsg(String queue, String content) {
        String key = getKey(queue);
        cacheManager.lRemove(key, content);
        log.info(LogUtil.getCommLog(String.format("删除消息，队列：%s，内容：%s", queue, content)));

        lockManager.unLock(queue);
    }


    /**
     * 获取key
     *
     * @param queue
     * @return
     */
    private String getKey(String queue) {
        return cacheManager.getKey(MQConfiguration.QUEUE_PREFIX + queue);
    }
}
