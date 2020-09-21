package com.jeffrey.vo.user;

import com.jeffrey.context.enums.OperateLogTypeEnum;
import com.jeffrey.context.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description: 用户操作日志
 *
 * @author WQ
 * @date 2020/8/27 9:43 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLogVO {

    /**
     * 操作类型-必选
     */
    private OperateLogTypeEnum operateLogTypeEnum;

    /**
     * 客户端类型
     */
    private PlatformEnum platform;

    /**
     * 客户端版本号
     */
    private Integer version;

    /**
     * 请求客户端IP
     */
    private String clientIp;

    /**
     * 操作人编号
     */
    private String operatorCode;

    /**
     * 操作业务主键
     */
    private String businessKey;

    /**
     * 操作房源编号
     */
    private String houseCode;

    /**
     * 操作房间编号
     */
    private String roomCode;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
