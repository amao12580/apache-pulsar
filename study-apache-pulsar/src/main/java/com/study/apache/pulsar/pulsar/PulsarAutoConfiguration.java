package com.study.apache.pulsar.pulsar;

import com.study.apache.pulsar.dto.request.message.MessageRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

import static com.study.apache.pulsar.conf.Constants.*;

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
public class PulsarAutoConfiguration {
    private PulsarClient client;

    private Producer<MessageRequestDto> messageRequestDtoProducer;

    @Bean
    PulsarClient createPulsarClient() throws PulsarClientException {
        client = PulsarClient.builder()
                .serviceUrl(SERVICE_URL)
                .allowTlsInsecureConnection(true)
                .build();
        return client;
    }

    @Bean
    Producer<MessageRequestDto> createMessageRequestDtoProducer(PulsarClient client) throws PulsarClientException {
        messageRequestDtoProducer = client.newProducer(JSONSchema.of(MessageRequestDto.class))
                .topic(TOPIC)
                .producerName(PRODUCER_NAME)
                .create();
        return messageRequestDtoProducer;
    }

    @PreDestroy
    void close() {
        try {
            messageRequestDtoProducer.close();
        } catch (PulsarClientException e) {
            log.error(e.getMessage(), e);
        }
        try {
            client.close();
        } catch (PulsarClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
