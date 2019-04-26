package com.study.apache.pulsar.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:17
 */
@Slf4j
@Api(tags = "测试consumer")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

}
