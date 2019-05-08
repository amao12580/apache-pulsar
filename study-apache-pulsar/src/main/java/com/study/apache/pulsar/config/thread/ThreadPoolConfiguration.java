package com.study.apache.pulsar.config.thread;

import com.study.apache.pulsar.config.base.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:11:40
 *
 * @author:steven
 */
@Slf4j
@EnableAsync
@Configuration
public class ThreadPoolConfiguration extends AsyncConfigurerSupport {

    @Resource
    private BeanFactory beanFactory;


    @Resource
    private ThreadPoolTaskExecutor executor;

    @Resource
    private LazyTraceExecutor lazyTraceExecutor;


    @Bean
    @Primary
    ThreadPoolTaskExecutor createThreadPoolTaskExecutor(Config config) {
        int cpuCoreCount = Runtime.getRuntime().availableProcessors();
        log.info("cpuCoreCount is " + cpuCoreCount);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(cpuCoreCount);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(cpuCoreCount * 2);
        // 队列大小
        threadPoolTaskExecutor.setQueueCapacity(cpuCoreCount * 10);
        // 线程最大空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        // 拒绝策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.setThreadNamePrefix(config.getApplicationName() + "-");
        threadPoolTaskExecutor.afterPropertiesSet();
        log.info("ThreadPoolTaskExecutor is ready to inject.");
        return threadPoolTaskExecutor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return lazyTraceExecutor;
    }

    @Bean
    public LazyTraceExecutor createLazyTraceExecutor() {
        return new LazyTraceExecutor(beanFactory, executor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable ex, Method method, Object... params) -> log.error(ex.getMessage(), ex);
    }
}
