package com.study.apache.pulsar.controller;


import com.study.apache.pulsar.dto.request.base.BaseRequestDto;
import com.study.apache.pulsar.dto.request.message.MessageRequestDto;
import com.study.apache.pulsar.dto.response.base.BaseResponseDto;
import com.study.apache.pulsar.dto.response.message.MessageResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:17
 */
@Slf4j
@Api(tags = "测试produce")
@RestController
@RequestMapping("/produce")
public class ProducerController {

    @ApiOperation(value = "发送一条消息")
    @PostMapping(value = "/simple")
    public BaseResponseDto<MessageResponseDto> simple(@RequestBody @ApiParam(value = "消息发送请求对象", required = true) BaseRequestDto<MessageRequestDto> messageRequestDto) {
        log.info(messageRequestDto.toString());
        return null;
    }
}
