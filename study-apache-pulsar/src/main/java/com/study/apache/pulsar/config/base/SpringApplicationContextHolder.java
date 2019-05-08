package com.study.apache.pulsar.config.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * @author steven
 */
@Slf4j
public class SpringApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取spring bean
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        Map<String, T> beans = applicationContext.getBeansOfType(clazz);
        if (beans.isEmpty()) {
            return null;
        }
        return beans.values().iterator().next();
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext 为空，请检查配置");
        }
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        SpringApplicationContextHolder.applicationContext = applicationContext;
        log.info("set springContext is success");
    }
}
