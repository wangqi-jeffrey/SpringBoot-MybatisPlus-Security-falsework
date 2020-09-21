package com.jeffrey.context.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 线程池
 *
 * @author WQ
 * @date 2020/9/12 12:02 PM
 */
@Data
@ConfigurationProperties(prefix = "config.thread-pool")
public class ThreadPoolProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueCapacity;

    private int keepAliveSeconds;
}
