package com.jeffrey.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * C端用户信息
 * </p>
 *
 * @author admin
 * @since 2020-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user")
public class User extends BaseEntity {


    /**
     * 用户编号
     */
    @TableField("f_user_code")
    private String userCode;

    /**
     * 手机号
     */
    @TableField("f_phone")
    private String phone;

    /**
     * 小程序appid
     */
    @TableField("f_appid")
    private String appid;

    /**
     * 小程序的openid
     */
    @TableField("f_open_id")
    private String openId;

    /**
     * unionid
     */
    @TableField("f_union_id")
    private String unionId;

    /**
     * 用户昵称
     */
    @TableField("f_nick_name")
    private String nickName;

    /**
     * 用户头像图片的 URL
     */
    @TableField("f_avatar_url")
    private String avatarUrl;

    /**
     * 用户性别
     */
    @TableField("f_gender")
    private Boolean gender;

    /**
     * 用户所在国家
     */
    @TableField("f_country")
    private String country;

    /**
     * 用户所在省份
     */
    @TableField("f_province")
    private String province;

    /**
     * 用户所在城市
     */
    @TableField("f_city")
    private String city;

    /**
     * 语言
     */
    @TableField("f_language")
    private String language;

    /**
     * 最后一次登录时间
     */
    @TableField("f_last_login_time")
    private Date lastLoginTime;


}
