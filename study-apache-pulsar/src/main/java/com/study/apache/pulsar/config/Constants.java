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

    /**
     * 死信队列
     */
    String TOPIC_DEAD_LETTER = "dead-letter-topic";

    String TOPIC_TEST = "my-topic-03";

    String CONSUMER_TEST = "my-consumer-03";

    String PRODUCER_NAME_TEST = "product-03";

    String DEFAULT_PRODUCES = MediaType.APPLICATION_JSON_UTF8_VALUE;
    String DEFAULT_CONSUMES = MediaType.APPLICATION_JSON_UTF8_VALUE;

}
