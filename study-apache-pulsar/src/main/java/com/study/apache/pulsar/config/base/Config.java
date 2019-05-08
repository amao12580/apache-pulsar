package com.study.apache.pulsar.config.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2018/5/18
 * Time:19:10
 *
 * @author steven
 */
@RefreshScope
@Slf4j
@Getter
@Component
public class Config {

    @Value("${spring.application.name}")
    private String applicationName;

    @PostConstruct
    public void initConstants() {
        log.info("当前应用名:" + applicationName);
    }
}
