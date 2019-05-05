package com.study.apache.pulsar.config.protocol;

import com.study.apache.pulsar.protocol.request.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:11:40
 *
 * @author:steven
 */
@Slf4j
@Configuration
public class ProtocolAutoConfiguration {
    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    BaseRequest createBaseRequest() {
        log.debug("BaseRequest created.");
        return new BaseRequest();
    }
}
