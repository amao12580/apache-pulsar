package com.study.apache.pulsar.config.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

import static com.study.apache.pulsar.config.Constants.SERVICE_URL;

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

    @Bean
    PulsarClient createPulsarClient() throws PulsarClientException {
        client = PulsarClient.builder()
                .serviceUrl(SERVICE_URL)
                .allowTlsInsecureConnection(true)
                .build();
        return client;
    }


    @PreDestroy
    void close() {
        log.info("关闭 PulsarClient");
        try {
            client.close();
        } catch (PulsarClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
