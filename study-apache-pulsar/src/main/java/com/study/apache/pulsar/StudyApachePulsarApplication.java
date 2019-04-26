package com.study.apache.pulsar;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author steven
 */
@EnableSwagger2Doc
@SpringBootApplication
public class StudyApachePulsarApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApachePulsarApplication.class, args);
    }

}
