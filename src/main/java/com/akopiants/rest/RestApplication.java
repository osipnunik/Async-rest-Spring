package com.akopiants.rest;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class RestApplication implements AsyncConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(4);
		taskExecutor.setMaxPoolSize(8);
		taskExecutor.setQueueCapacity(500);
		taskExecutor.setThreadNamePrefix("ScriptTh");
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		
		return new SimpleAsyncUncaughtExceptionHandler();
	}
	
}
