package com.study.apache.pulsar.controller;


import com.alibaba.fastjson.JSON;
import com.study.apache.pulsar.dto.request.message.MessageRequestDto;
import com.study.apache.pulsar.dto.response.message.MessageResponseDto;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2029/4/26
 * Time:15:17
 *
 * @author steven
 */
@Slf4j
@Api(tags = "测试 produce")
@RestController
@RequestMapping("/produce")
public class ProducerController {

    @Resource
    Producer<Object> producer;

    @ApiOperation(value = "发送一条消息")
    @PostMapping(value = "/simple")
    public BaseResponse<MessageResponseDto> simple(@RequestBody @ApiParam(value = "消息发送请求对象", required = true) BaseRequest<MessageRequestDto> messageRequestDto) throws PulsarClientException {
        log.info("messageRequestDto:{}", messageRequestDto.toString());
        MessageRequestDto data = messageRequestDto.getBody().getData();
        log.info("data:{}", data.toString());
        producer.send(JSON.toJSONString(data));
        log.info("numMsgsSent:{}", producer.getStats().getNumMsgsSent());
        log.info(producer.getProducerName());
        log.info(producer.getTopic());
        log.info("lastSequenceId:{}", producer.getLastSequenceId());
        return BaseResponse.newInstance(messageRequestDto, ResponseBody.success(new MessageResponseDto(
                String.valueOf(System.currentTimeMillis()))));
    }
}
