package com.jeffrey.context.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  * Description:
 *  * @author 金泽强
 *  * @date 2020/09/16 10:27 上午
 *  
 */
@Data
@ConfigurationProperties("config.jx-saas")
@Component
public class JXSaasProperties {
    private String domain;
    private String secretKey;
}
