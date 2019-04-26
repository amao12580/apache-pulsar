package com.study.apache.pulsar.dto.request.message;

import com.study.apache.pulsar.dto.request.base.BaseRequestDataDto;
import com.study.apache.pulsar.dto.request.message.enums.MessageRequestEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("消息发送请求参数对象")
public class MessageRequestDto extends BaseRequestDataDto {
    @ApiModelProperty(value = "消息类型", required = true, example = "SIMPLE")
    MessageRequestEnum type;
    @ApiModelProperty(value = "消息接收者", required = true)
    String destination;
    @ApiModelProperty(value = "标题")
    String title;
    @ApiModelProperty(value = "内容", required = true)
    String content;
}
