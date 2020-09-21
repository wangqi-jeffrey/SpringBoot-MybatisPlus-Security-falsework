package com.jeffrey.manager;

import com.jeffrey.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Description: 企业微信
 *
 * @author WQ
 * @date 2020/9/16 8:29 PM
 */
@Slf4j
@Component
public class QiWechatManager {

    /**
     * Redis队列消费报警机器人
     */
    public static final String REDIS_QUEUE_CONSUMER_ROBOT = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=80c82917-aafd-4968-bfa7-666dfaccac2c";

    @Autowired
    private Environment environment;

    public void sendQueueWarning(String queueName, String traceId, Exception e) {
        String content = String.format("queue：%s \\n" +
                "traceId：%s \\n" +
                "env：%s \\n" +
                "msg：%s", queueName, traceId, environment.getProperty("spring.profiles.active"), e.getMessage());
        String msg = "{\n" +
                "  \"msgtype\": \"text\",\n" +
                "  \"text\": {\n" +
                "    \"content\": \"" + content + "\",\n" +
                "    \"mentioned_list\":[\"@all\"]" +
                "  }\n" +
                "}";
        HttpUtil.postWithJson(REDIS_QUEUE_CONSUMER_ROBOT, msg);
    }

}