package com.jeffrey.context.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: Sms Properties
 *
 * @author WQ
 * @date 2020/8/21 11:32 AM
 */
@Data
@ConfigurationProperties("config.sms")
@Component
public class SmsProperties {

    private String domain;

    private String signId;

    private String verifyTemplateId;

    private Long verifyCodeEffectiveTime;

}
