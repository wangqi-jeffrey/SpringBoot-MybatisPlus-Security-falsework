package com.jeffrey.dto;

import lombok.Data;

/**
 * Description: B端管理员账户信息，内部传参使用
 *
 * @author WQ
 * @date 2020/08/20 9:06 PM
 */
@Data
public class ManagerAccountDTO {

    /**
     * 管理员编号
     */
    private String managerCode;

    /**
     * 账户类型：0-房东 1-管家
     */
    private Integer accountType;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户状态：0:有效；1:无效
     */
    private Integer status;

}