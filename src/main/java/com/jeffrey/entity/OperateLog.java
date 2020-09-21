package com.jeffrey.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户操作日志表
 * </p>
 *
 * @author admin
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_operate_log")
public class OperateLog extends BaseEntity {


    /**
     * 客户端类型
     */
    @TableField("f_platform")
    private String platform;

    /**
     * 客户端版本号
     */
    @TableField("f_version")
    private Integer version;

    /**
     * 请求客户端IP
     */
    @TableField("f_client_ip")
    private String clientIp;

    /**
     * 所属模块编号
     */
    @TableField("f_module_code")
    private String moduleCode;

    /**
     * 操作类型编号
     */
    @TableField("f_operate_type_code")
    private String operateTypeCode;

    /**
     * 操作人编号
     */
    @TableField("f_operator_code")
    private String operatorCode;

    /**
     * 操作业务主键
     */
    @TableField("f_business_key")
    private String businessKey;

    /**
     * 操作房源编号
     */
    @TableField("f_house_code")
    private String houseCode;

    /**
     * 操作房间编号
     */
    @TableField("f_room_code")
    private String roomCode;

    /**
     * 操作内容
     */
    @TableField("f_content")
    private String content;


}
