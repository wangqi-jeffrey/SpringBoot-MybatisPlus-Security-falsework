package com.jeffrey.manager.mq.producer;

/**
 * Description: 消息生产者
 *
 * @author TGD
 * @date 2020/08/26 下午 15:40
 */
public interface MQProducer {
    /**
     * 发布队列消息
     *
     * @param queue   队列名字
     * @param content 消息内容
     */
    boolean sendMsg(String queue, String content);

    /**
     * 发布主题消息
     *
     * @param topic   主题
     * @param content 消息内容
     */
    void publish(String topic, String content);
}
