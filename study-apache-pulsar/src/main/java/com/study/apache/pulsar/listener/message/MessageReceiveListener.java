package com.study.apache.pulsar.listener.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/28
 * Time:14:59
 * <p>
 *
 * @author:steven
 */
@Slf4j
@Component
public class MessageReceiveListener implements MessageListener<String> {

    @Override
    public void received(Consumer<String> consumer, Message<String> message) {
        log.info("{}", consumer.getStats().getNumMsgsReceived());
        log.info(message.getTopicName());
        log.info(message.getProducerName());
        log.info(message.getKey());
        log.info("{}", message.getEventTime());
        log.info(message.getMessageId().toString());
        log.info("消息内容:{}", message.getValue());
        try {
            consumer.acknowledge(message);
        } catch (PulsarClientException e) {
            consumer.redeliverUnacknowledgedMessages();
            e.printStackTrace();
            log.error(e.getMessage(), e);
            //当异常出现的不能容忍时，可以选择：1.暂停收取消息，2.断开订阅通道，3.关闭所有功能
        }
    }
}
