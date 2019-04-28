package com.study.apache.pulsar.config.pulsar.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

import static com.study.apache.pulsar.config.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/28
 * Time:15:01
 * <p>
 *
 * @author:steven
 */
@Slf4j
@Configuration
public class ConsumerAutoConfiguration {
    private Consumer<String> consumer;

    @Autowired
    private MessageListener<String> messageListener;

    @Bean
    Consumer<String> createConsumer(PulsarClient client) throws PulsarClientException {
        consumer = client.newConsumer(JSONSchema.of(String.class))
                .topic(TOPIC_TEST)
                .subscriptionName(PRODUCER_NAME_TEST)
                .ackTimeout(30, TimeUnit.SECONDS)
                .messageListener(messageListener)
                .subscriptionType(SubscriptionType.Failover)
                .deadLetterPolicy(DeadLetterPolicy.builder()
                        .maxRedeliverCount(3)
                        .deadLetterTopic(TOPIC_DEAD_LETTER)
                        .build())
                .consumerName(CONSUMER_TEST)
                .subscribe();
        return consumer;
    }

    @PreDestroy
    void close() {
        log.info("关闭 Consumer");
        try {
            consumer.close();
        } catch (PulsarClientException e) {
            log.error(e.getMessage(), e);
        }
    }

}
