package com.study.apache.pulsar.config;

import org.springframework.http.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:19:08
 *
 * @author steven
 */
public interface Constants {
    String SERVICE_URL = "pulsar://192.168.1.231:6650";
    String TOPIC = "my-topic-02";
    String PRODUCER_NAME = "product-02";

    String DEFAULT_PRODUCES = MediaType.APPLICATION_JSON_UTF8_VALUE;
    String DEFAULT_CONSUMES = MediaType.APPLICATION_JSON_UTF8_VALUE;

}
