package com.study.apache.pulsar.dto.request.message.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:25
 *
 * @author steven
 */
@Getter
@ApiModel("消息类型枚举")
public enum MessageRequestEnum {
    //简单消息，接收者明确
    @ApiModelProperty(value = "简单消息，接收者明确")
    SIMPLE,
    //i转发消息，需要有转发目的地
    @ApiModelProperty(value = "转发消息，需要有转发目的地")
    FORWARD,
    //主题类型消息，接收者不定
    @ApiModelProperty(value = "主题类型消息，接收者不定")
    TOPIC
}
