package com.jeffrey.context.configuration;

import com.jeffrey.context.properties.ThreadPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Description: 线程池
 *
 * @author WQ
 * @date 2020/9/12 12:02 PM
 */
@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
@ConditionalOnClass(ThreadPoolProperties.class)
public class ThreadPoolClient {

	@Autowired
	private ThreadPoolProperties threadPoolProperties;

	@Bean
	public ThreadPoolTaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
		taskExecutor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
		taskExecutor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
		taskExecutor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
		taskExecutor.setThreadFactory(tr -> new Thread(tr, "async-thread-pool_zfgj_" + tr.hashCode()));
		taskExecutor.initialize();
		return taskExecutor;
	}

}
