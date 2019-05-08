package com.study.apache.pulsar;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:19:13
 *
 * @author steven
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2Doc
@SpringBootApplication
public class StudyApachePulsarApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApachePulsarApplication.class, args);
    }

}
