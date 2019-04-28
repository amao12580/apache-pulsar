package com.study.apache.pulsar.config.pulsar.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

import static com.study.apache.pulsar.config.Constants.PRODUCER_NAME_TEST;
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
                .batchingMaxPublishDelay(1, TimeUnit.SECONDS)
                .enableBatching(true)
                .sendTimeout(3, TimeUnit.SECONDS)
                .producerName(PRODUCER_NAME_TEST)
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
