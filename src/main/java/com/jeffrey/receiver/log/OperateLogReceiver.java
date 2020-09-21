package com.jeffrey.receiver.log;

import com.jeffrey.context.configuration.MQConfiguration;
import com.jeffrey.manager.OperateLogManager;
import com.jeffrey.receiver.AbstractReceiver;
import com.jeffrey.utils.json.GsonUtil;
import com.jeffrey.vo.user.OperateLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 处理操作日志消息
 *
 * @author WQ
 * @date 2020/8/27 9:48 AM
 */
@Slf4j
@Component
public class OperateLogReceiver extends AbstractReceiver {

    @Autowired
    private OperateLogManager operateLogManager;

    /**
     * 获取队列名字
     *
     * @return
     */
    @Override
    protected String getQueue() {
        return MQConfiguration.QUEUE_USER_OPERATE_LOG;
    }

    /**
     * 处理消息
     *
     * @param content
     */
    @Override
    protected void handle(String content) {
        OperateLogVO operateLogVO = GsonUtil.jsonToBean(content, OperateLogVO.class);
        operateLogManager.addOneLog(operateLogVO);
    }
}
