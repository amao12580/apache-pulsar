package com.study.apache.pulsar.controller.message;


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
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.study.apache.pulsar.config.Constants.DEFAULT_PRODUCES;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2029/4/26
 * Time:15:17
 *
 * @author steven
 */
@Slf4j
@Api(value = "发送消息控制器", tags = {"消息队列发送接口"})
@RestController
@RequestMapping("/message//queue/send")
public class MessageSendController {

    @Resource
    Producer<String> producer;

    @ApiOperation(value = "发送单条消息", produces = DEFAULT_PRODUCES)
    @PostMapping(value = "/simple")
    public BaseResponse<MessageResponseDto> simple(@RequestBody @ApiParam(value = "消息发送请求参数", required = true) BaseRequest<MessageRequestDto> messageRequestDto) throws PulsarClientException {
        log.info("messageRequestDto:{}", messageRequestDto.toString());
        MessageRequestDto data = messageRequestDto.getBody().getData();
        log.info("data:{}", data.toString());
        MessageId messageId = producer.newMessage().value(JSON.toJSONString(data)).send();
        log.info("numMsgsSent:{}", producer.getStats().getNumMsgsSent());
        log.info(producer.getProducerName());
        log.info(producer.getTopic());
        log.info("lastSequenceId:{}", producer.getLastSequenceId());
        return BaseResponse.newInstance(messageRequestDto, ResponseBody.success(new MessageResponseDto(messageId.toString())));
    }

    @ApiOperation(value = "批量发送消息", produces = DEFAULT_PRODUCES)
    @PostMapping(value = "/batch")
    public BaseResponse<Void> batch(@RequestBody @ApiParam(value = "消息发送请求参数", required = true) BaseRequest<List<MessageRequestDto>> messageRequestDto) {
        log.info("messageRequestDto:{}", messageRequestDto.toString());
        List<MessageRequestDto> dtos = messageRequestDto.getBody().getData();
        for (MessageRequestDto data : dtos) {
            log.info("data:{}", data.toString());
            producer.newMessage().value(JSON.toJSONString(data)).sendAsync();
        }
        log.info("numMsgsSent:{}", producer.getStats().getNumMsgsSent());
        log.info(producer.getProducerName());
        log.info(producer.getTopic());
        log.info("lastSequenceId:{}", producer.getLastSequenceId());
        return BaseResponse.newInstance(messageRequestDto, ResponseBody.success());
    }
}
