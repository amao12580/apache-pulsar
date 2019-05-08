package com.study.apache.pulsar.config.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static com.study.apache.pulsar.config.Constants.TIMEZONE_DEFAULT;

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
public class BaseAutoConfiguration {
    @PostConstruct
    void timezone() {
        TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_DEFAULT));
        log.info("timezone set to " + TIMEZONE_DEFAULT);
    }

    @Bean
    public SpringApplicationContextHolder springContextHolder() {
        log.info("SpringApplicationContextHolder is ready to inject.");
        return new SpringApplicationContextHolder();
    }
}
