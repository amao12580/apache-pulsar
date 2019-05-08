package com.study.apache.pulsar.interfaces;

import com.study.apache.pulsar.dto.request.message.MessageRequestDto;
import com.study.apache.pulsar.dto.response.message.MessageResponseDto;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.study.apache.pulsar.config.Constants.DEFAULT_PRODUCES;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/5/7
 * Time:10:29
 *
 * @author:steven
 */
@Api(value = "发送消息控制器", tags = {"消息队列发送接口"})
@FeignClient(name = "service-message-client")
public interface MessageSendInterface {
    String MAPPING = "/message/queue/send/";

    /**
     * 发送单条消息
     *
     * @param messageRequestDto 消息数据
     * @return 发送结果
     * @throws PulsarClientException 发送异常
     */
    @ApiOperation(value = "发送单条消息", produces = DEFAULT_PRODUCES)
    @PostMapping(value = MAPPING + "simple")
    BaseResponse<MessageResponseDto> simple(@RequestBody @ApiParam(value = "消息发送请求参数", required = true) BaseRequest<MessageRequestDto> messageRequestDto) throws PulsarClientException;


    /**
     * 批量发送消息
     *
     * @param messageRequestDto 消息数据
     * @return 发送结果
     */
    @ApiOperation(value = "批量发送消息", produces = DEFAULT_PRODUCES)
    @PostMapping(value = MAPPING + "batch")
    BaseResponse<Void> batch(@RequestBody @ApiParam(value = "消息发送请求参数", required = true) BaseRequest<List<MessageRequestDto>> messageRequestDto);

}
