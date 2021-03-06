package com.study.apache.pulsar.config.pulsar.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.UUID;
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

    @Resource
    private MessageListener<String> messageListener;

    @Bean
    Consumer<String> createConsumer(PulsarClient client) throws PulsarClientException {
        consumer = client.newConsumer(Schema.STRING)
                .topic(TOPIC_TEST)
                .subscriptionName(PRODUCER_NAME_TEST)
                .ackTimeout(30, TimeUnit.SECONDS)
                .messageListener(messageListener)
                .subscriptionType(SubscriptionType.Failover)
                .readCompacted(true)
                .autoUpdatePartitions(true)
                .maxTotalReceiverQueueSizeAcrossPartitions(10 * 1000)
                .receiverQueueSize(1000)
                .subscriptionInitialPosition(SubscriptionInitialPosition.Latest)
                .deadLetterPolicy(DeadLetterPolicy.builder()
                        .maxRedeliverCount(3)
                        .deadLetterTopic(TOPIC_DEAD_LETTER)
                        .build())
                .consumerName(UUID.randomUUID().toString())
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
