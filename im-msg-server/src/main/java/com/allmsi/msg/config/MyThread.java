package com.allmsi.msg.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class MyThread {
	@Value("${sendThreadPool.num}")
	private int threadNum;

	@Bean(name = "asyncExecutor")
	public ThreadPoolTaskExecutor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(threadNum);
		executor.setThreadNamePrefix("mqExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 对拒绝task的处理策略
		executor.initialize();
		System.out.println("启用线程池");
		return executor;
	}

}
