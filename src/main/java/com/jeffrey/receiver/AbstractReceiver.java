package com.jeffrey.receiver;

import com.jeffrey.manager.QiWechatManager;
import com.jeffrey.manager.mq.consumer.MQConsumer;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Description: 消息消费者
 *
 * @author TGD
 * @date 2020/09/01 上午 11:38
 */
@Slf4j
public abstract class AbstractReceiver {
    public static final String TRACE_ID = "TRACE_ID";

    @Autowired
    private MQConsumer mqConsumer;

    @Autowired
    private QiWechatManager qiWechatManager;

    @PostConstruct
    public void run() {
        new Thread(() -> {
            while (true) {
                String traceId = UUID.randomUUID().toString();
                MDC.put(TRACE_ID, traceId);
                String receiveMsg = null;
                try {
                    Thread.sleep(1000);
                    receiveMsg = mqConsumer.receiveMsg(getQueue());

                    if (StringUtil.isBlank(receiveMsg)) {
                        continue;
                    }
                    log.info(LogUtil.getCommLog(String.format("接收消息：queue = %s, content = %s", getQueue(), receiveMsg)));

                    handle(receiveMsg);
                    log.info(LogUtil.getCommLog(String.format("消费消息成功：queue = %s, content = %s", getQueue(), receiveMsg)));
                    mqConsumer.deleteMsg(getQueue(), receiveMsg);
                    log.info(LogUtil.getCommLog(String.format("删除消息：queue = %s, content = %s", getQueue(), receiveMsg)));
                } catch (Exception e) {
                    log.error(LogUtil.getCommLog(String.format("消息处理出现异常：queue = %s, content = %s, %s", getQueue(), receiveMsg, ExceptionUtils.getStackTrace(e))));
                    // 发送企业微信消息
                    qiWechatManager.sendQueueWarning(getQueue(), traceId, e);
                }
                MDC.remove(TRACE_ID);
            }
        }).start();
    }

    /**
     * 获取队列名字
     *
     * @return
     */
    protected abstract String getQueue();

    /**
     * 处理消息
     *
     * @param content
     */
    protected abstract void handle(String content);
}
