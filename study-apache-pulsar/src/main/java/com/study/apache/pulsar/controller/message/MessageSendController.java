package com.study.apache.pulsar.controller.message;


import com.alibaba.fastjson.JSON;
import com.study.apache.pulsar.dto.request.message.MessageRequestDto;
import com.study.apache.pulsar.dto.response.message.MessageResponseDto;
import com.study.apache.pulsar.interfaces.MessageSendInterface;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2029/4/26
 * Time:15:17
 *
 * @author steven
 */
@Slf4j
@RestController
public class MessageSendController implements MessageSendInterface {

    @Resource
    Producer<String> producer;

    @Override
    public BaseResponse<MessageResponseDto> simple(BaseRequest<MessageRequestDto> messageRequestDto) throws PulsarClientException {
        log.info("messageRequestDto:{}", messageRequestDto.toString());
        MessageRequestDto data = messageRequestDto.getBody().getData();
        log.info("data:{}", data.toString());
        MessageId messageId = producer.newMessage().value(JSON.toJSONString(data)).send();
        log.info("numMsgsSent:{}", producer.getStats().getNumMsgsSent());
        log.info(producer.getProducerName());
        log.info(producer.getTopic());
        log.info("lastSequenceId:{}", producer.getLastSequenceId());
        return BaseResponse.newInstance(ResponseBody.success(new MessageResponseDto(messageId.toString())));
    }

    @Override
    public BaseResponse<Void> batch(BaseRequest<List<MessageRequestDto>> messageRequestDto) {
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
        return BaseResponse.newInstance(ResponseBody.success());
    }
}
