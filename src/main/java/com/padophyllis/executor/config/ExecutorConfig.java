package com.padophyllis.executor.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@SpringBootConfiguration
@EnableAsync
public class ExecutorConfig {

    @Bean
    public ExecutorService getThreadPool(){
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        return threadPool;
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置线程池核心容量
        executor.setCorePoolSize(4);
        // 设置线程池最大容量
        executor.setMaxPoolSize(8);
        // 设置任务队列长度
        executor.setQueueCapacity(200);
        // 设置线程超时时间
        executor.setKeepAliveSeconds(60);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("xyjAsyncPool-");
        // 设置任务丢弃后的处理策略,当poolSize已达到maxPoolSize，如何处理新任务（是拒绝还是交由其它线程处理）,CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}