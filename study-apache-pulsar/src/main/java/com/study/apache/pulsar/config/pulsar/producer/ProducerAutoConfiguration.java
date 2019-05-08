package com.study.apache.pulsar.config.pulsar.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.study.apache.pulsar.config.Constants.TOPIC_TEST;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:19:13
 *
 * @author steven
 */
@Slf4j
@Configuration
public class ProducerAutoConfiguration {
    private Producer<String> producer;

    @Bean
    Producer<String> createProducer(PulsarClient client) throws PulsarClientException {
        producer = client.newProducer(Schema.STRING)
                .topic(TOPIC_TEST)
                .batchingMaxMessages(1000)
                .autoUpdatePartitions(true)
                .blockIfQueueFull(false)
                .compressionType(CompressionType.LZ4)
                .messageRoutingMode(MessageRoutingMode.RoundRobinPartition)
                .hashingScheme(HashingScheme.Murmur3_32Hash)
                .batchingMaxPublishDelay(1, TimeUnit.SECONDS)
                .enableBatching(true)
                .sendTimeout(3, TimeUnit.SECONDS)
                .producerName(UUID.randomUUID().toString())
                .create();
        return producer;
    }

    @PreDestroy
    void close() {
        log.info("关闭 Producer");
        try {
            producer.close();
        } catch (PulsarClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
