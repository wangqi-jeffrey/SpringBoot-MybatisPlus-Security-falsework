package com.jeffrey.manager.mq.consumer;

/**
 * Description: 消息消费者
 *
 * @author TGD
 * @date 2020/08/26 下午 16:08
 */
public interface MQConsumer {
    /**
     * 接收队列消息
     *
     * @param queue
     * @return
     */
    String receiveMsg(String queue) throws Exception;

    /**
     * 删除队列消息
     *
     * @param queue
     * @param content
     */
    void deleteMsg(String queue, String content);
}
