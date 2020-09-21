package com.jeffrey.manager.mq.producer;

import com.jeffrey.context.configuration.MQConfiguration;
import com.jeffrey.manager.CacheManager;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: Redis消息生产者
 *
 * @author TGD
 * @date 2020/08/26 下午 16:14
 */
@Slf4j
@Component
public class RedisMQProducer implements MQProducer {
    @Autowired
    private CacheManager cacheManager;

    /**
     * 发布队列消息
     *
     * @param queue   队列名字
     * @param content 消息内容
     */
    @Override
    public boolean sendMsg(String queue, String content) {
        String key = getKey(queue);
        if (StringUtil.isNotBlank(content) && cacheManager.lSet(key, content)) {
            log.info(LogUtil.getCommLog(String.format("发送队列消息成功，队列：%s，内容：%s", queue, content)));
            return true;
        } else {
            log.error(LogUtil.getCommLog(String.format("发送队列消息失败，队列：%s，内容：%s", queue, content)));
            return false;
        }
    }

    /**
     * 发布主题消息
     *
     * @param topic   主题
     * @param content 消息内容
     */
    @Override
    public void publish(String topic, String content) {
        List<String> queues = MQConfiguration.TOPIC_MAP.get(topic);
        if (StringUtil.isNotBlank(content) && CollectionUtils.isNotEmpty(queues)) {
            for (String queue : queues) {
                String key = getKey(queue);
                cacheManager.lSet(key, content);
            }
        }

        log.info(LogUtil.getCommLog(String.format("发送主题消息成功，主题：%s，内容：%s", topic, content)));
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
