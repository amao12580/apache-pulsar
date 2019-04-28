package com.study.apache.pulsar.config.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

import static com.study.apache.pulsar.config.Constants.SERVICE_ADMIN_URL;

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
public class PulsarAdminAutoConfiguration {
    private PulsarAdmin admin;

    @Bean
    PulsarAdmin createPulsarAdmin() throws PulsarClientException {
        admin = PulsarAdmin.builder()
                .serviceHttpUrl(SERVICE_ADMIN_URL)
                .connectionTimeout(3, TimeUnit.SECONDS)
                .allowTlsInsecureConnection(true)
                .build();
        return admin;
    }


    @PreDestroy
    void close() {
        log.info("关闭 PulsarAdmin");
        admin.close();
    }
}
