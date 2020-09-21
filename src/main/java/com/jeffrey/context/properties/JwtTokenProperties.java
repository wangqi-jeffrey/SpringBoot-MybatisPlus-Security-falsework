package com.jeffrey.context.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: JWT Properties
 *
 * @author WQ
 * @date 2020/8/20 4:08 PM
 */
@Data
@ConfigurationProperties("config.jwt")
@Component
public class JwtTokenProperties {

    private String tokenHeader;

    private String tokenPathMatcher;

    // APP
    private long tokenExpiredTime;
    // Mini-APP
    private long tokenExpiredTimeMiniApp;

    private String aliasKey;

    private String password;

    private String keystorePath;

}
