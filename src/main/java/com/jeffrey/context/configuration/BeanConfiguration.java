package com.jeffrey.context.configuration;

import com.jeffrey.context.properties.AliyunOSSProperties;
import com.jeffrey.manager.OssManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Description: Bean注入
 *
 * @author WQ
 * @date 2020/09/03 10:05 PM
 */
@Slf4j
@Configuration
public class BeanConfiguration {

    @Primary
    @Bean("aliyunOSSProperties")
    @ConfigurationProperties(prefix = "aliyun.oss")
    public AliyunOSSProperties aliyunOSSProperties() {
        return new AliyunOSSProperties();
    }

    @Bean("ccbOSSProperties")
    @ConfigurationProperties(prefix = "aliyun.oss-ccb")
    public AliyunOSSProperties ccbOSSProperties() {
        return new AliyunOSSProperties();
    }

    @Bean("ccbOssManager")
    public OssManager ccbOssManager(@Qualifier("ccbOSSProperties") AliyunOSSProperties ccbOSSProperties) {
        return new OssManager(ccbOSSProperties);
    }

    @Primary
    @Bean("ossManager")
    public OssManager ossManager(@Qualifier("aliyunOSSProperties") AliyunOSSProperties aliyunOSSProperties) {
        return new OssManager(aliyunOSSProperties);
    }
}