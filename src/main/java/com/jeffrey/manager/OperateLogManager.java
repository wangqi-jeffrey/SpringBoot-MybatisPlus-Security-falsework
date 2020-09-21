package com.jeffrey.manager;

import com.jeffrey.context.HttpServletRequestContextHolder;
import com.jeffrey.context.configuration.MQConfiguration;
import com.jeffrey.context.constant.CommonConstant;
import com.jeffrey.context.enums.OperateLogTypeEnum;
import com.jeffrey.dto.ManagerAccountDTO;
import com.jeffrey.dto.UserInfoDTO;
import com.jeffrey.dto.request.BaseRequestDTO;
import com.jeffrey.entity.OperateLog;
import com.jeffrey.manager.mq.producer.MQProducer;
import com.jeffrey.mapper.user.OperateLogMapper;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.json.GsonUtil;
import com.jeffrey.vo.user.OperateLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Description: 用户操作日志
 *
 * @author WQ
 * @date 2020/08/27 9:31 AM
 */
@Slf4j
@Component
public class OperateLogManager {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private MQProducer mqProducer;

    /**
     * 操作日志记录
     *
     * @param operateLogVO
     * @param baseRequestDTO 非主线程写入需要传，baseRequestDTO不为null时，优先取baseRequestDTO参数
     */
    public <T extends BaseRequestDTO> void log(OperateLogVO operateLogVO, T baseRequestDTO) {
        Date now = new Date();
        try {
            if (baseRequestDTO == null) {
                // 请求入参是否继承自BaseRequestDTO
                baseRequestDTO = (T) httpServletRequest.getAttribute(CommonConstant.BASE_REQUEST_ATTR);
                if (baseRequestDTO == null) {
                    baseRequestDTO = (T) HttpServletRequestContextHolder.getBaseRequestAttributes(httpServletRequest);
                }
            }
            ManagerAccountDTO managerAccountDTO = baseRequestDTO.getManagerAccountDTO();
            UserInfoDTO userInfoDTO = baseRequestDTO.getUserInfoDTO();
            if (managerAccountDTO != null) {
                operateLogVO.setOperatorCode(managerAccountDTO.getManagerCode());
            } else if (userInfoDTO != null) {
                operateLogVO.setOperatorCode(userInfoDTO.getUserCode());
            }
            operateLogVO.setPlatform(baseRequestDTO.getPlatform());
            operateLogVO.setVersion(baseRequestDTO.getVersion());
            operateLogVO.setClientIp(baseRequestDTO.getClientIp());

            operateLogVO.setCreateTime(now);
            operateLogVO.setUpdateTime(now);
        } catch (Exception e) {
            log.error(LogUtil.getCommLog("记录用户操作日志出现异常", e));
            return;
        }
        mqProducer.sendMsg(MQConfiguration.QUEUE_USER_OPERATE_LOG,
                GsonUtil.toJson(operateLogVO));
    }

    /**
     * 操作日志记录
     *
     * @param operateLogVO 登录状态下的操作可不填 operatorCode
     */
    public void log(OperateLogVO operateLogVO) {
        this.log(operateLogVO, null);
    }

    /**
     * 添加一条日志
     *
     * @param operateLogVO
     */
    public void addOneLog(OperateLogVO operateLogVO) {
        OperateLogTypeEnum operateLogTypeEnum = operateLogVO.getOperateLogTypeEnum();
        OperateLog operateLog = new OperateLog();
        BeanUtils.copyProperties(operateLogVO, operateLog, "operateLogTypeEnum", "platform");
        operateLog.setModuleCode(operateLogTypeEnum.getModule().name());
        operateLog.setOperateTypeCode(operateLogTypeEnum.name());
        operateLog.setPlatform(operateLogVO.getPlatform().name());
        operateLogMapper.insert(operateLog);
    }
}