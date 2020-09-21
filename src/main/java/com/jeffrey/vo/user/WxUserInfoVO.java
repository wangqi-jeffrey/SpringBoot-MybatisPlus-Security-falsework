package com.jeffrey.vo.user;

import lombok.Data;

/**
 * Description: 微信用户信息
 *
 * @author WQ
 * @date 2020/08/29 10:12 AM
 */
@Data
public class WxUserInfoVO {
    private String openId;
    private String nickName;
    private String gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private String phone;
    private String appid;
}