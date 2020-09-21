package com.jeffrey.context.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 微信公众号配置
 *
 * @author WQ
 * @date 2020/9/10 9:57 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {
    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;

    /**
     * 催收消息模板id
     */
    private String collectionMsgTplId;

}