package com.jeffrey.dto;

import lombok.Data;

/**
 * Description: C端用户信息，内部传参使用
 *
 * @author WQ
 * @date 2020/08/29 12:29 PM
 */
@Data
public class UserInfoDTO {

    private Integer id;

    private String userCode;

    private String phone;

    private String openId;
}