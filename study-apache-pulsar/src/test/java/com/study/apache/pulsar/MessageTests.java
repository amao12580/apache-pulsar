package com.study.apache.pulsar;


import com.study.apache.pulsar.dto.request.message.MessageRequestDto;
import com.study.apache.pulsar.dto.request.message.enums.MessageRequestEnum;
import com.study.apache.pulsar.dto.response.message.MessageResponseDto;
import com.study.apache.pulsar.interfaces.MessageSendInterface;
import com.study.apache.pulsar.protocol.common.algorithm.AlgorithmTypeEnum;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.request.body.RequestBody;
import com.study.apache.pulsar.protocol.request.enums.device.RequestDeviceEnum;
import com.study.apache.pulsar.protocol.request.foot.RequestFoot;
import com.study.apache.pulsar.protocol.request.header.RequestHeader;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StudyApachePulsarApplication.class)
class MessageTests {

    @Resource
    MessageSendInterface messageSendInterface;

    /**
     * 简单消息发送
     * <p>
     * 重复执行10次
     * <p>
     * 并发执行
     */
    @RepeatedTest(10)
    @Execution(ExecutionMode.CONCURRENT)
    void worker() throws PulsarClientException {
        BaseRequest<MessageRequestDto> request = BaseRequest.newInstance(
                RequestHeader
                        .builder()
                        .algorithm(AlgorithmTypeEnum.SIMPLE)
                        .id(UUID.randomUUID().toString())
                        .timestamp(new Date())
                        .type(RequestDeviceEnum.APP_IOS)
                        .build(),
                RequestBody.newInstance(
                        MessageRequestDto
                                .builder()
                                .content(UUID.randomUUID().toString())
                                .destination(UUID.randomUUID().toString())
                                .title(UUID.randomUUID().toString())
                                .type(MessageRequestEnum.TOPIC)
                                .build()),
                RequestFoot
                        .builder()
                        .sign(UUID.randomUUID().toString())
                        .build());

        log.info(request.toString());
        BaseResponse<MessageResponseDto> response = messageSendInterface.simple(request);
        log.info(response.toString());
    }
}
